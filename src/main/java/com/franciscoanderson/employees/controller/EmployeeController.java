package com.franciscoanderson.employees.controller;


import com.franciscoanderson.employees.entity.Employee;
import com.franciscoanderson.employees.repository.EmployeeRepository;
import com.franciscoanderson.employees.vo.EmployeeVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 *
 *
 * @author anderson
 *
 */
@RestController
@RequestMapping("api/employers")
@Api(description = "Endpoints para consulta de funcionarios.")
public class EmployeeController {


    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping
    @ApiOperation("Retorna todos os funcionarios cadastrados")
    @ApiResponses({@ApiResponse(code = 204,message = "Sem Registros para retornar",response = String.class)})
    public ResponseEntity<List<EmployeeVO>> findEmployees(){

        List<Employee> employees = employeeRepository.findAll();

        if(employees.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(employees
                .stream()
                .map(EmployeeVO::new)
                .collect(Collectors.toList()));

    }


    @GetMapping("{id}")
    @ApiOperation("Retorna o funcionario cadastrado pelo id")
    public ResponseEntity<EmployeeVO> findEmployee(@ApiParam("id do funcionario") @PathVariable("id")String id){

        Optional<Employee> employer = employeeRepository.findById(id);

        return employer
                .map(EmployeeVO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ApiOperation("Cadastra um novo funcionario")
    public ResponseEntity<EmployeeVO> createEmployer(@RequestBody EmployeeVO employeeVO){

        Employee employee = employeeRepository.save(new Employee(employeeVO));


        return ResponseEntity.created(URI.create("api/employers/" + employee.getId()))
                .body(new EmployeeVO(employee));


    }

    @PutMapping("{id}")
    @ApiOperation("Atualiza os dados de um funcionario")
    public ResponseEntity<EmployeeVO> updateEmployer(
            @ApiParam("id do funcionario")@PathVariable("id") String id,
            @RequestBody EmployeeVO employeeVO
    ){

        Optional<Employee> employerOptional = employeeRepository.findById(id);

        if(!employerOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Employee employee = employerOptional.get();

        Employee employeeUpdate = new Employee(employeeVO);
        employeeUpdate.setId(employee.getId());

        employeeRepository.save(employeeUpdate);

        return ResponseEntity.ok().build();


    }







}

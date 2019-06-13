package com.franciscoanderson.employees.controller;


import com.franciscoanderson.employees.service.EmployeeExternalService;
import com.franciscoanderson.employees.vo.EmployeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 *
 * @author anderson
 *
 */
@RestController
@RequestMapping("api/employers-external")
@Api(description = "Endpoints para consulta de funcionarios cadastrados externamente.")
public class EmployeeExternalController {


    @Autowired
    private EmployeeExternalService employeeExternalService;


    @GetMapping
    @ApiOperation("Retorna todos os funcionarios cadastrados")
    @ApiResponses({@ApiResponse(code = 204,message = "Sem Registros para retornar",response = String.class)})
    public ResponseEntity<List<EmployeeVO>> findEmployees(){

        List<EmployeeVO> employees = employeeExternalService.findAllEmployeesExternal();

        if(employees.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(employees);

    }

}

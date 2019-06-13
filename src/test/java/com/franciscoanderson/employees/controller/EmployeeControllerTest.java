package com.franciscoanderson.employees.controller;


import com.franciscoanderson.employees.entity.Employee;
import com.franciscoanderson.employees.repository.EmployeeRepository;
import com.franciscoanderson.employees.vo.EmployeeVO;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author anderson
 *
 */
@DataMongoTest
@RunWith(SpringRunner.class)
@Import({EmployeeController.class})
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void should_create_new_employee(){

        EmployeeVO employeeVO = EmployeeVO.builder()
                .employeeName("João das Neves")
                .employeeSalary("1400.00")
                .employeeAge("28")
                .build();

        ResponseEntity<EmployeeVO> responseEntity = employeeController.createEmployer(employeeVO);

        Assert.assertNotNull(responseEntity);

        Assert.assertEquals(201,responseEntity.getStatusCodeValue());

        EmployeeVO body = responseEntity.getBody();

        Assert.assertNotNull(body);

        Assert.assertNotNull(body.getId());

        Assert.assertEquals("João das Neves",body.getEmployeeName());

        Assert.assertEquals("1400.00",body.getEmployeeSalary());

        Assert.assertEquals("28",body.getEmployeeAge());

        Assert.assertNull(body.getProfileImage());


    }

    @Test
    public void should_find_all_employers(){

        employeeRepository.deleteAll();

        Assert.assertEquals(0, employeeRepository.count());

        Employee employee1 = Employee.builder()
                .age(23)
                .name("Maria")
                .salary(5400.25)
                .build();

        Employee employee2 = Employee.builder()
                .name("Wesley")
                .salary(2500.90)
                .age(19)
                .build();

        Employee employee3 = Employee.builder()
                .name("Pedro")
                .salary(4500.00)
                .age(30)
                .build();

        employeeRepository.saveAll(Lists.newArrayList(employee1, employee2, employee3));

        ResponseEntity<List<EmployeeVO>> responseEntity = employeeController.findEmployees();

        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

        List<EmployeeVO> responseEntityBody = responseEntity.getBody();

        Assert.assertNotNull(responseEntityBody);

        Assert.assertEquals(3,responseEntityBody.size());


    }


    @Test
    public void should_update_a_employer(){

        Employee employee1 = Employee.builder()
                .age(23)
                .name("Maria")
                .salary(5400.25)
                .build();

        employee1 = employeeRepository.save(employee1);

        Assert.assertNotNull(employee1);

        EmployeeVO updateVo = new EmployeeVO(employee1);
        updateVo.setEmployeeSalary("10000.00");

        ResponseEntity<EmployeeVO> responseEntity = employeeController.updateEmployer(employee1.getId(), updateVo);

        Assert.assertNotNull(responseEntity);

        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

        Optional<Employee> updatedEmployer = employeeRepository.findById(updateVo.getId());

        Assert.assertNotNull(updatedEmployer);

        Assert.assertTrue(updatedEmployer.isPresent());

        Assert.assertEquals(10000.00,updatedEmployer.get().getSalary(),0);


    }

    @Test
    public void should_find_a_employer(){

        Employee employee1 = Employee.builder()
                .age(28)
                .name("Joao")
                .salary(7200.00)
                .build();

        employee1 = employeeRepository.save(employee1);

        Assert.assertNotNull(employee1);

        ResponseEntity<EmployeeVO> responseEntity = employeeController.findEmployee(employee1.getId());

        Assert.assertNotNull(responseEntity);

        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

        EmployeeVO employeeVO = responseEntity.getBody();

        Assert.assertNotNull(employeeVO);

        Assert.assertEquals("Joao", employeeVO.getEmployeeName());

        Assert.assertEquals("28", employeeVO.getEmployeeAge());

        Assert.assertEquals("7200.00", employeeVO.getEmployeeSalary());


    }

    @Test
    public void should_not_find_employer(){

        ResponseEntity<EmployeeVO> responseEntity = employeeController.findEmployee("null");

        Assert.assertNotNull(responseEntity);

        Assert.assertEquals(404,responseEntity.getStatusCodeValue());

    }




}

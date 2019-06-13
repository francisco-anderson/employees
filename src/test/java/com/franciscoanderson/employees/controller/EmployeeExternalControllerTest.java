package com.franciscoanderson.employees.controller;


import com.franciscoanderson.employees.service.EmployeeExternalService;
import com.franciscoanderson.employees.vo.EmployeeVO;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

/**
 *
 *
 * @author anderson
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({EmployeeExternalController.class, EmployeeExternalControllerTest.TestConfiguration.class})
public class EmployeeExternalControllerTest {


    @Autowired
    private EmployeeExternalController employeeExternalController;

    @Autowired
    private EmployeeExternalService employeeExternalService;


    @Configuration
    public static class TestConfiguration {

        @Bean
        @Primary
        public EmployeeExternalService employeeExternalService(){
            return Mockito.mock(EmployeeExternalService.class);
        }

    }


    @Test
    public void should_find_all_external_employers(){

        Mockito.reset(employeeExternalService);

        List<EmployeeVO> employeeVOList = ImmutableList
                .of(EmployeeVO.builder()
                        .employeeName("Joao")
                        .employeeSalary("154.12")
                        .employeeAge("23")
                        .build());

        Mockito.when(employeeExternalService.findAllEmployeesExternal())
                .thenReturn(employeeVOList);

        ResponseEntity<List<EmployeeVO>> responseEntity = employeeExternalController.findEmployees();

        Assert.assertNotNull(responseEntity);

        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

        List<EmployeeVO> body = responseEntity.getBody();

        Assert.assertNotNull(body);

        Assert.assertEquals(1,body.size());

        EmployeeVO employeeVO = body.get(0);

        Assert.assertNotNull(employeeVO);

        Assert.assertEquals("Joao",employeeVO.getEmployeeName());

        Assert.assertEquals("154.12",employeeVO.getEmployeeSalary());

        Assert.assertEquals("23",employeeVO.getEmployeeAge());


    }

    @Test
    public void should_return_not_found(){

        Mockito.reset(employeeExternalService);

        Mockito.when(employeeExternalService.findAllEmployeesExternal())
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<EmployeeVO>> responseEntity = employeeExternalController.findEmployees();

        Assert.assertNotNull(responseEntity);

        Assert.assertEquals(204,responseEntity.getStatusCodeValue());

    }



}

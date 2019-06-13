package com.franciscoanderson.employees.service;


import com.franciscoanderson.employees.client.EmployeeExternalClient;
import com.franciscoanderson.employees.vo.EmployeeVO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;


/**
 *
 *
 *
 * @author anderson
 *
 */
@Service
public class EmployeeExternalServiceImpl implements EmployeeExternalService {


    @Autowired
    private EmployeeExternalClient employeeExternalClient;


    @Override
    @SneakyThrows
    public List<EmployeeVO> findAllEmployeesExternal(){

        Call<List<EmployeeVO>> call = employeeExternalClient.findAllEmployees();

        Response<List<EmployeeVO>> response = call.execute();

        if(!response.isSuccessful()){
            throw new IllegalStateException("Não foi possível consultar o serviço externo.");
        }

        return response.body();

    }


}

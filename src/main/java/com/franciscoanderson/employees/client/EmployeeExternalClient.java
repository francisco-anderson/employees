package com.franciscoanderson.employees.client;


import com.franciscoanderson.employees.vo.EmployeeVO;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;


/**
 *
 *
 * @author anderson
 *
 */
public interface EmployeeExternalClient {


    @GET("api/v1/employees")
    Call<List<EmployeeVO>> findAllEmployees();

}

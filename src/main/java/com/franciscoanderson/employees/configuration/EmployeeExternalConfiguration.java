package com.franciscoanderson.employees.configuration;


import com.franciscoanderson.employees.client.EmployeeExternalClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 *
 *
 * @author anderson
 *
 */
@Configuration
public class EmployeeExternalConfiguration {


    @Value("${employees.baseurl}")
    private String baseUrl;


    @Bean
    public EmployeeExternalClient employeeExternalClient(){

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(EmployeeExternalClient.class);

    }

}

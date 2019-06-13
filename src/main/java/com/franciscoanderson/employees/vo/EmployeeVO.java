package com.franciscoanderson.employees.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.franciscoanderson.employees.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {

    private String id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private String employeeSalary;
    @JsonProperty("employee_age")
    private String employeeAge;
    @JsonProperty("profile_image")
    private String profileImage;


    public EmployeeVO(Employee employee){
        this.id = employee.getId();
        this.employeeName = employee.getName();
        this.employeeSalary = String.format(Locale.ENGLISH,"%.2f", employee.getSalary());
        this.employeeAge = String.valueOf(employee.getAge());
        this.profileImage = employee.getProfileImage();
    }

}

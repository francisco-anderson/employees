package com.franciscoanderson.employees.entity;


import com.franciscoanderson.employees.vo.EmployeeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


/**
 *
 *
 * @author anderson
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    private String id;
    private String name;
    private double salary;
    private int age;
    private String profileImage;

    public Employee(EmployeeVO employeeVO){
        this.id = employeeVO.getId();
        this.name = employeeVO.getEmployeeName();
        this.salary = Double.parseDouble(employeeVO.getEmployeeSalary());
        this.age = Integer.parseInt(employeeVO.getEmployeeAge());
        this.profileImage = employeeVO.getProfileImage();
    }


}

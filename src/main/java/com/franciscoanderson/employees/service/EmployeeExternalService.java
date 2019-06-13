package com.franciscoanderson.employees.service;


import com.franciscoanderson.employees.vo.EmployeeVO;

import java.util.List;

/**
 *
 *
 * @author anderson
 *
 */
public interface EmployeeExternalService {

    List<EmployeeVO> findAllEmployeesExternal();

}

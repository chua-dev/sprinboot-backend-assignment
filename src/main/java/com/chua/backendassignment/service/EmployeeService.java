package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(Long deptId, EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployeeOfDepartment(Long deptId);

    void deleteAllEmployeeOfDepartment(Long deptId);
}

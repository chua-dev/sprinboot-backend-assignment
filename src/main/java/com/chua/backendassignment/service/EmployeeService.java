package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(Long groupId, EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployeeOfGroup(Long groupId);

    void deleteAllEmployeeOfGroup(Long groupId);
}

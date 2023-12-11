package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.DepartmentDto;
import com.chua.backendassignment.dto.DepartmentAndEmployeesDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getDepartments(Integer pageNo, Integer pageSize, String sortBy);

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> createDepartments(List<DepartmentDto> departmentDtos);

    DepartmentAndEmployeesDto createDepartmentAndEmployees(DepartmentAndEmployeesDto departmentAndEmployeesDto);

    DepartmentDto updateDepartment(Long deptId, DepartmentDto departmentDto);

    DepartmentDto getDepartment(Long deptId);

    DepartmentDto deleteDepartment(Long deptId);

    DepartmentDto findByName(String name);

    boolean existDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getActiveDepartment();

    List<DepartmentDto> getDescriptionContains(String description);
}

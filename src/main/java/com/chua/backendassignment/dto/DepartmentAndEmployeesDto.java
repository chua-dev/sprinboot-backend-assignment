package com.chua.backendassignment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DepartmentAndEmployeesDto {
    private DepartmentDto departmentDto;
    private List<EmployeeDto> employeeDtoList;
}

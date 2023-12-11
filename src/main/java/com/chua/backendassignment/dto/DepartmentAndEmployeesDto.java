package com.chua.backendassignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DepartmentAndEmployeesDto {
    @JsonProperty("department_dto")
    private DepartmentDto departmentDto;
    @JsonProperty("employee_dto_list")
    private List<EmployeeDto> employeeDtoList;
}

package com.chua.backendassignment.dto;

import com.chua.backendassignment.dto.EmployeeDto;
import com.chua.backendassignment.dto.GroupDto;
import com.chua.backendassignment.model.Group;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupAndEmployeesDto {
    private GroupDto groupDto;
    private List<EmployeeDto> employeeDtoList;
}

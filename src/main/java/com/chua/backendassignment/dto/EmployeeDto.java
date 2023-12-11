package com.chua.backendassignment.dto;

import com.chua.backendassignment.model.Department;
import com.chua.backendassignment.model.util.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    @Email
    private String email;
    private Integer age;
    @JsonIgnore
    private Department department;
}

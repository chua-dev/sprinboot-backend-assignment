package com.chua.backendassignment.dto;

import com.chua.backendassignment.model.Department;
import com.chua.backendassignment.model.util.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private Gender gender;
    @Email
    private String email;
    private Integer age;
    @JsonIgnore
    private Department department;
}

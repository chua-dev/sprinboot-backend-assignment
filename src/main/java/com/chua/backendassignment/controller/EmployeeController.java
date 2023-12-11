package com.chua.backendassignment.controller;

import com.chua.backendassignment.dto.EmployeeDto;
import com.chua.backendassignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/group/{groupId}/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@PathVariable(value = "groupId") Long groupId,
                                                      @RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDtoResult = employeeService.createEmployee(groupId, employeeDto);
        return new ResponseEntity<>(employeeDtoResult, HttpStatus.CREATED);
    }

    @GetMapping("/group/{groupId}/employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeByGroupId(@PathVariable(value = "groupId") Long groupId){
        return new ResponseEntity<>(employeeService.getAllEmployeeOfGroup(groupId), HttpStatus.OK);
    }

    @DeleteMapping("/group/{groupId}/employee")
    public ResponseEntity<Void> deleteAllEmployeeByGroupId(@PathVariable(value = "groupId")Long groupId){
        employeeService.deleteAllEmployeeOfGroup(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

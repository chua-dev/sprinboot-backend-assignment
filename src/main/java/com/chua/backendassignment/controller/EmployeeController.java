package com.chua.backendassignment.controller;

import com.chua.backendassignment.dto.EmployeeDto;
import com.chua.backendassignment.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/department/{deptId}/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@PathVariable(value = "deptId") Long deptId,
                                                      @RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDtoResult = employeeService.createEmployee(deptId, employeeDto);
        return new ResponseEntity<>(employeeDtoResult, HttpStatus.CREATED);
    }

    @GetMapping("/department/{deptId}/employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeByDeptId(@PathVariable(value = "deptId") Long deptId){
        return new ResponseEntity<>(employeeService.getAllEmployeeOfDepartment(deptId), HttpStatus.OK);
    }

    @DeleteMapping("/department/{deptId}/employee")
    public ResponseEntity<Void> deleteAllEmployeeByDeptId(@PathVariable(value = "deptId")Long deptId){
        employeeService.deleteAllEmployeeOfDepartment(deptId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

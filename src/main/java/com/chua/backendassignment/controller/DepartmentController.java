package com.chua.backendassignment.controller;

import com.chua.backendassignment.dto.DepartmentDto;
import com.chua.backendassignment.dto.DepartmentAndEmployeesDto;
import com.chua.backendassignment.model.response.GeolocationResponse;
import com.chua.backendassignment.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    // Pagination Api
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
        List<DepartmentDto> departments = departmentService.getDepartments(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Third Party Api
    @GetMapping("/thirdPartyApi")
    public ResponseEntity<GeolocationResponse> getGeolocationByIp(){
        String uri = "https://ipapi.co/json/";
        RestTemplate restTemplate = new RestTemplate();
        GeolocationResponse response = restTemplate.getForObject(uri, GeolocationResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(value = "deptId") Long deptId){
        DepartmentDto departmentDto = departmentService.getDepartment(deptId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto createdDepartmentDto = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(departmentDto, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public ResponseEntity<List<DepartmentDto>> createDepartments(@RequestBody List<DepartmentDto> departmentDtos){
        List<DepartmentDto> departmentDtosResult = departmentService.createDepartments(departmentDtos);
        return new ResponseEntity<>(departmentDtosResult, HttpStatus.CREATED);
    }

    @PostMapping("/create/departmentAndEmployee")
    public ResponseEntity<DepartmentAndEmployeesDto> createDepartmentAndEmployees(@RequestBody DepartmentAndEmployeesDto departmentAndEmployeesDto){
        DepartmentAndEmployeesDto result = departmentService.createDepartmentAndEmployees(departmentAndEmployeesDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping("/{deptId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable(value = "deptId") Long deptId, @RequestBody DepartmentDto department){
        DepartmentDto departmentDto = departmentService.updateDepartment(deptId, department);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @DeleteMapping("/{deptId}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable(value = "deptId") Long deptId){
        DepartmentDto departmentDto = departmentService.deleteDepartment(deptId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<DepartmentDto> getDepartmentByName(@RequestParam(value = "name") String name){
        DepartmentDto departmentDto = departmentService.findByName(name);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @GetMapping(params = "description")
    public ResponseEntity<List<DepartmentDto>> getDescriptionContain(@RequestParam(value = "description") String description){
        List<DepartmentDto> departmentListDto = departmentService.getDescriptionContains(description);
        return new ResponseEntity<>(departmentListDto, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<DepartmentDto>> getActiveDepartment(){
        List<DepartmentDto> activeDepartment = departmentService.getActiveDepartment();
        return new ResponseEntity<>(activeDepartment, HttpStatus.OK);
    }

    @PostMapping("/exist")
    public ResponseEntity<Boolean> departmentExist(@RequestBody DepartmentDto departmentDto){
        Boolean isDepartmentExist = departmentService.existDepartment(departmentDto);
        return new ResponseEntity<>(isDepartmentExist, HttpStatus.OK);
    }



}

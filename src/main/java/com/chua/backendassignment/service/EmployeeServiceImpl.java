package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.EmployeeDto;
import com.chua.backendassignment.exception.ResourceNotFoundException;
import com.chua.backendassignment.model.Employee;
import com.chua.backendassignment.model.Department;
import com.chua.backendassignment.repository.EmployeeRepository;
import com.chua.backendassignment.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public EmployeeDto createEmployee(Long deptId, EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", deptId));
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setDepartment(department);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployeeOfDepartment(Long deptId) {
        if (!departmentRepository.existsById(deptId)){
            throw new ResourceNotFoundException("Department", "id", deptId);
        }

        return employeeRepository.findByDepartmentId(deptId).stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAllEmployeeOfDepartment(Long deptId) {
        if (!departmentRepository.existsById(deptId)){
            throw new ResourceNotFoundException("Department", "id", deptId);
        }

        employeeRepository.deleteByDepartmentId(deptId);
    }
}

package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.EmployeeDto;
import com.chua.backendassignment.exception.ResourceNotFoundException;
import com.chua.backendassignment.model.Employee;
import com.chua.backendassignment.model.Group;
import com.chua.backendassignment.repository.EmployeeRepository;
import com.chua.backendassignment.repository.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, GroupRepository groupRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public EmployeeDto createEmployee(Long groupId, EmployeeDto employeeDto) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group", "id", groupId));
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setGroup(group);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployeeOfGroup(Long groupId) {
        if (!groupRepository.existsById(groupId)){
            throw new ResourceNotFoundException("Group", "id", groupId);
        }

        return employeeRepository.findByGroupId(groupId).stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllEmployeeOfGroup(Long groupId) {
        if (!groupRepository.existsById(groupId)){
            throw new ResourceNotFoundException("Group", "id", groupId);
        }

        employeeRepository.deleteByGroupId(groupId);
    }
}

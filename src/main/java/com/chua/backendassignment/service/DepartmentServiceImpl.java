package com.chua.backendassignment.service;

import com.chua.backendassignment.controller.DepartmentController;
import com.chua.backendassignment.dto.EmployeeDto;
import com.chua.backendassignment.dto.DepartmentDto;
import com.chua.backendassignment.exception.ResourceNotFoundException;
import com.chua.backendassignment.model.Employee;
import com.chua.backendassignment.model.Department;
import com.chua.backendassignment.dto.DepartmentAndEmployeesDto;
import com.chua.backendassignment.repository.EmployeeRepository;
import com.chua.backendassignment.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getDepartments(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        List<Department> departments = departmentRepository.findAll();
        logger.info("test" + departments.size());

        return departmentRepository.findAll(pageable).getContent()
                .stream().map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    @Transactional
    public List<DepartmentDto> createDepartments(List<DepartmentDto> departmentDtos) {

        List<DepartmentDto> departmentDtoResult = new ArrayList<>();

        for (DepartmentDto departmentDto : departmentDtos){
            Department department = modelMapper.map(departmentDto, Department.class);
            departmentRepository.save(department);
            departmentDtoResult.add(modelMapper.map(department, DepartmentDto.class));
        }

        return departmentDtoResult;
    }

    @Override
    @Transactional
    public DepartmentAndEmployeesDto createDepartmentAndEmployees(DepartmentAndEmployeesDto departmentAndEmployeesDto){
        Department department = modelMapper.map(departmentAndEmployeesDto.getDepartmentDto(), Department.class);
        department = departmentRepository.save(department);

        DepartmentAndEmployeesDto departmentAndEmployeesDtoResult = DepartmentAndEmployeesDto.builder().build();
        departmentAndEmployeesDtoResult.setDepartmentDto(modelMapper.map(department, DepartmentDto.class));
        departmentAndEmployeesDtoResult.setEmployeeDtoList(new ArrayList<EmployeeDto>());

        for (EmployeeDto employeeDto: departmentAndEmployeesDto.getEmployeeDtoList()){
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            employee.setDepartment(department);
            employeeRepository.save(employee);
            departmentAndEmployeesDtoResult.getEmployeeDtoList().add(modelMapper.map(employee, EmployeeDto.class));
        }

        return departmentAndEmployeesDtoResult;
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(Long deptId, DepartmentDto departmentDto) {

        Department departmentFromDb = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", deptId));

        departmentFromDb.setName(departmentDto.getName());
        departmentFromDb.setDescription(departmentDto.getDescription());
        departmentFromDb.setCode(departmentDto.getCode());
        departmentFromDb.setActive(departmentDto.isActive());

        Department updatedDepartment = departmentRepository.save(departmentFromDb);

        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartment(Long deptId) {
        Department departmentFromDb = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", deptId));

        return modelMapper.map(departmentFromDb, DepartmentDto.class);
    }

    @Override
    public DepartmentDto deleteDepartment(Long deptId) {

        Department departmentFromDb = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", deptId));
        departmentRepository.delete(departmentFromDb);

        return modelMapper.map(departmentFromDb, DepartmentDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto findByName(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "name", name));
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existDepartment(DepartmentDto departmentDto) {
        return departmentRepository.existsByCode(departmentDto.getCode())
                && departmentRepository.existsByName(departmentDto.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getActiveDepartment() {
        return departmentRepository.findByActiveTrue()
                .stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getDescriptionContains(String description) {
        return departmentRepository.findByDescriptionContaining(description)
                .stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

}

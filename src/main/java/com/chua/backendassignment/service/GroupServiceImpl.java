package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.EmployeeDto;
import com.chua.backendassignment.dto.GroupDto;
import com.chua.backendassignment.exception.ResourceNotFoundException;
import com.chua.backendassignment.model.Employee;
import com.chua.backendassignment.model.Group;
import com.chua.backendassignment.dto.GroupAndEmployeesDto;
import com.chua.backendassignment.repository.EmployeeRepository;
import com.chua.backendassignment.repository.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public GroupServiceImpl(GroupRepository groupRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.groupRepository = groupRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> getGroups(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return groupRepository.findAll(pageable).getContent()
                .stream().map(group -> modelMapper.map(group, GroupDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = modelMapper.map(groupDto, Group.class);
        Group savedGroup = groupRepository.save(group);
        return modelMapper.map(savedGroup, GroupDto.class);
    }

    @Override
    @Transactional
    public List<GroupDto> createGroups(List<GroupDto> groupDtos) {

        List<GroupDto> groupDtoResult = new ArrayList<>();

        for (GroupDto groupDto : groupDtos){
            Group group = modelMapper.map(groupDto, Group.class);
            groupRepository.save(group);
            groupDtoResult.add(modelMapper.map(group, GroupDto.class));
        }

        return groupDtoResult;
    }

    @Override
    @Transactional
    public GroupAndEmployeesDto createGroupAndEmployees(GroupAndEmployeesDto groupAndEmployeesDto){
        Group group = modelMapper.map(groupAndEmployeesDto.getGroupDto(), Group.class);
        group = groupRepository.save(group);

        GroupAndEmployeesDto groupAndEmployeesDtoResult = GroupAndEmployeesDto.builder().build();
        groupAndEmployeesDtoResult.setGroupDto(modelMapper.map(group, GroupDto.class));
        groupAndEmployeesDtoResult.setEmployeeDtoList(new ArrayList<EmployeeDto>());

        for (EmployeeDto employeeDto: groupAndEmployeesDto.getEmployeeDtoList()){
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            employee.setGroup(group);
            employeeRepository.save(employee);
            groupAndEmployeesDtoResult.getEmployeeDtoList().add(modelMapper.map(employee, EmployeeDto.class));
        }

        return groupAndEmployeesDtoResult;
    }

    @Override
    @Transactional
    public GroupDto updateGroup(Long groupId, GroupDto groupDto) {

        Group groupFromDb = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group", "id", groupId));

        groupFromDb.setName(groupDto.getName());
        groupFromDb.setDescription(groupDto.getDescription());
        groupFromDb.setCode(groupDto.getCode());
        groupFromDb.setActive(groupDto.isActive());

        Group updatedGroup = groupRepository.save(groupFromDb);

        return modelMapper.map(updatedGroup, GroupDto.class);
    }

    @Override
    public GroupDto getGroup(Long groupId) {
        Group groupFromDb = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group", "id", groupId));

        return modelMapper.map(groupFromDb, GroupDto.class);
    }

    @Override
    public GroupDto deleteGroup(Long groupId) {

        Group groupFromDb = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group", "id", groupId));
        groupRepository.delete(groupFromDb);

        return modelMapper.map(groupFromDb, GroupDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDto findByName(String name) {
        Group group = groupRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Group", "name", name));
        return modelMapper.map(group, GroupDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existGroup(GroupDto groupDto) {
        return groupRepository.existsByCode(groupDto.getCode())
                && groupRepository.existsByName(groupDto.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> getActiveGroups() {
        return groupRepository.findByActiveTrue()
                .stream()
                .map(group -> modelMapper.map(group, GroupDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> getDescriptionContains(String description) {
        return groupRepository.findByDescriptionContaining(description)
                .stream()
                .map(group -> modelMapper.map(group, GroupDto.class))
                .collect(Collectors.toList());
    }

}

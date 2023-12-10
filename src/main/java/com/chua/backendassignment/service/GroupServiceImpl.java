package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.GroupDto;
import com.chua.backendassignment.exception.ResourceNotFoundException;
import com.chua.backendassignment.model.Group;
import com.chua.backendassignment.repository.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    public GroupServiceImpl(GroupRepository groupRepository, ModelMapper modelMapper){
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
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
    public GroupDto findByName(String name) {
        Group group = groupRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Group", "name", name));
        return modelMapper.map(group, GroupDto.class);
    }

    @Override
    public boolean existGroup(GroupDto groupDto) {
        return groupRepository.existsByCode(groupDto.getCode())
                && groupRepository.existsByName(groupDto.getName());
    }

    @Override
    public List<GroupDto> getActiveGroups() {
        return groupRepository.findByActiveTrue()
                .stream()
                .map(group -> modelMapper.map(group, GroupDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupDto> getDescriptionContains(String description) {
        return groupRepository.findByDescriptionContaining(description)
                .stream()
                .map(group -> modelMapper.map(group, GroupDto.class))
                .collect(Collectors.toList());
    }

}

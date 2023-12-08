package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.GroupDto;
import com.chua.backendassignment.exception.EntityNotFoundException;
import com.chua.backendassignment.model.Group;
import com.chua.backendassignment.repository.GroupRepository;
import org.modelmapper.ModelMapper;
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
    public List<GroupDto> getGroups() {
        return groupRepository.findAll().stream().map(group ->
            modelMapper.map(group, GroupDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Long groupId, Group group) {

        Group groupFromDb = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group", "id", groupId));

        groupFromDb.setName(group.getName());
        groupFromDb.setDescription(group.getDescription());
        groupFromDb.setCode(group.getCode());
        groupFromDb.setActive(group.isActive());

        return groupRepository.save(groupFromDb);
    }

    @Override
    public Group getGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group", "id", groupId));
    }

    @Override
    public Group deleteGroup(Long groupId) {

        Group groupFromDb = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group", "id", groupId));
        groupRepository.delete(groupFromDb);

        return groupFromDb;
    }

    @Override
    public Group findByName(String name) {
        Group group = groupRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Group", "name", name));
        return null;
    }

    @Override
    public boolean existGroup(Group group) {
        return groupRepository.existsByCode(group.getCode())
                && groupRepository.existsByName(group.getName());
    }

    @Override
    public List<Group> getActiveGroups() {
        return groupRepository.findByActiveTrue();
    }

    @Override
    public List<Group> getDescriptionContains(String description) {
        return groupRepository.findByDescriptionContaining(description);
    }

}

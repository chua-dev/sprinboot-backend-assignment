package com.chua.backendassignment.service;

import com.chua.backendassignment.exception.EntityNotFoundException;
import com.chua.backendassignment.model.Group;
import com.chua.backendassignment.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getGroups() {
        return groupRepository.findAll();
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
        return null;
    }

    @Override
    public Group findByName(String name) {
        return null;
    }

    @Override
    public boolean existGroup(String name) {
        return false;
    }

    @Override
    public List<Group> getActiveGroups() {
        return null;
    }

    @Override
    public List<Group> getDescriptionContains(String Name) {
        return null;
    }

}

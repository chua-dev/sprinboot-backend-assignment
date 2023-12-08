package com.chua.backendassignment.service;

import com.chua.backendassignment.model.Group;

import java.util.List;

public interface GroupService {
    List<Group> getGroups();

    Group createGroup(Group group);

    Group updateGroup(Long groupId, Group group);

    Group getGroup(Long groupId);

    Group deleteGroup(Long groupId);

    Group findByName(String name);

    boolean existGroup(String name);

    List<Group> getActiveGroups();

    List<Group> getDescriptionContains(String Name);
}

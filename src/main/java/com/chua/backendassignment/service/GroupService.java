package com.chua.backendassignment.service;

import com.chua.backendassignment.dto.GroupDto;
import com.chua.backendassignment.dto.GroupAndEmployeesDto;

import java.util.List;

public interface GroupService {
    List<GroupDto> getGroups(Integer pageNo, Integer pageSize, String sortBy);

    GroupDto createGroup(GroupDto group);

    List<GroupDto> createGroups(List<GroupDto> groups);

    GroupAndEmployeesDto createGroupAndEmployees(GroupAndEmployeesDto groupAndEmployeesDto);

    GroupDto updateGroup(Long groupId, GroupDto group);

    GroupDto getGroup(Long groupId);

    GroupDto deleteGroup(Long groupId);

    GroupDto findByName(String name);

    boolean existGroup(GroupDto group);

    List<GroupDto> getActiveGroups();

    List<GroupDto> getDescriptionContains(String description);
}

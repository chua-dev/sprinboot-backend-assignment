package com.chua.backendassignment.controller;

import com.chua.backendassignment.dto.GroupDto;
import com.chua.backendassignment.model.Group;
import com.chua.backendassignment.service.GroupService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups(){
        List<GroupDto> groups = groupService.getGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable(name = "groupId") Long groupId){
        GroupDto groupDto = groupService.getGroup(groupId);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto){
        GroupDto createdGroupDto = groupService.createGroup(groupDto);
        return new ResponseEntity<>(groupDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable(name = "groupId") Long groupId, @RequestBody GroupDto group){
        GroupDto groupDto = groupService.updateGroup(groupId, group);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable(name = "groupId") Long groupId){
        GroupDto groupDto = groupService.deleteGroup(groupId);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<GroupDto> getGroupByName(@RequestParam(name = "name") String name){
        GroupDto groupDto = groupService.findByName(name);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<GroupDto>> getActiveGroup(){
        List<GroupDto> activeGroup = groupService.getActiveGroups();
        return new ResponseEntity<>(activeGroup, HttpStatus.OK);
    }

    @PostMapping("/exist")
    public ResponseEntity<Boolean> groupExist(@RequestBody GroupDto groupDto){
        Boolean isGroupExist = groupService.existGroup(groupDto);
        return new ResponseEntity<>(isGroupExist, HttpStatus.OK);
    }

    @GetMapping(params = "description")
    public ResponseEntity<List<GroupDto>> getDescriptionContain(@RequestParam(name = "description") String description){
        List<GroupDto> groupListDto = groupService.getDescriptionContains(description);
        return new ResponseEntity<>(groupListDto, HttpStatus.OK);
    }

}

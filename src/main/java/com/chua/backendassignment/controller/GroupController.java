package com.chua.backendassignment.controller;

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
    public ResponseEntity<List<Group>> getGroups(){
        List<Group> groups = groupService.getGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Group> getGroupById(@PathVariable Long groupId){
        Group group = groupService.getGroup(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group){
        Group createdGroup = groupService.createGroup(group);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Group> updateGroup(@PathVariable Long groupId, @RequestBody Group group){
        Group updatedGroup = groupService.updateGroup(groupId, group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Group> deleteGroup(@PathVariable Long groupId){
        Group deletedGroup = groupService.deleteGroup(groupId);
        return new ResponseEntity<>(deletedGroup, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Group>> getActiveGroup(){
        List<Group> activeGroup = groupService.getActiveGroups();
        return new ResponseEntity<>(activeGroup, HttpStatus.OK);
    }

    public

}

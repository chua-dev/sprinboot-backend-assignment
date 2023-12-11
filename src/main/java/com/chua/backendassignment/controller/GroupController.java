package com.chua.backendassignment.controller;

import com.chua.backendassignment.dto.GroupDto;
import com.chua.backendassignment.dto.GroupAndEmployeesDto;
import com.chua.backendassignment.model.response.GeolocationResponse;
import com.chua.backendassignment.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    Logger logger = LoggerFactory.getLogger(GroupController.class);

    private final GroupService groupService;

    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    // Pagination Api
    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
        List<GroupDto> groups = groupService.getGroups(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // Third Party Api
    @GetMapping("/thirdPartyApi")
    public ResponseEntity<GeolocationResponse> getGeolocationByIp(){
        String uri = "https://ipapi.co/json/";
        RestTemplate restTemplate = new RestTemplate();
        GeolocationResponse response = restTemplate.getForObject(uri, GeolocationResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable(value = "groupId") Long groupId){
        GroupDto groupDto = groupService.getGroup(groupId);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto){
        GroupDto createdGroupDto = groupService.createGroup(groupDto);
        return new ResponseEntity<>(groupDto, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public ResponseEntity<List<GroupDto>> createGroups(@RequestBody List<GroupDto> groupDtos){
        List<GroupDto> groupDtosResult = groupService.createGroups(groupDtos);
        return new ResponseEntity<>(groupDtosResult, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<GroupAndEmployeesDto> createGroupAndEmployees(@RequestBody GroupAndEmployeesDto groupAndEmployeesDto){
        GroupAndEmployeesDto result = groupService.createGroupAndEmployees(groupAndEmployeesDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping("/{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable(value = "groupId") Long groupId, @RequestBody GroupDto group){
        GroupDto groupDto = groupService.updateGroup(groupId, group);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable(value = "groupId") Long groupId){
        GroupDto groupDto = groupService.deleteGroup(groupId);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<GroupDto> getGroupByName(@RequestParam(value = "name") String name){
        GroupDto groupDto = groupService.findByName(name);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @GetMapping(params = "description")
    public ResponseEntity<List<GroupDto>> getDescriptionContain(@RequestParam(value = "description") String description){
        List<GroupDto> groupListDto = groupService.getDescriptionContains(description);
        return new ResponseEntity<>(groupListDto, HttpStatus.OK);
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



}

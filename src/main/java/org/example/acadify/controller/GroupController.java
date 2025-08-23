package org.example.acadify.controller;

import org.example.acadify.DTOs.GroupDTO;
import org.example.acadify.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public ResponseEntity<GroupDTO> createNewGroup(@RequestParam String name) {
        GroupDTO groupDTO = groupService.createGroup(name);
        return new ResponseEntity<>(groupDTO, HttpStatus.CREATED);
    }

    @PostMapping("/student/add-to-group")
    public ResponseEntity<GroupDTO> addStudentToGroup(@RequestParam String email, @RequestParam String newGroupName) {
        GroupDTO groupDTO = groupService.changeStudentGroup(email, newGroupName);
        return new ResponseEntity<>(groupDTO, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }
}

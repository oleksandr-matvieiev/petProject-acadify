package org.example.acadify.controller;

import org.example.acadify.DTOs.GroupDTO;
import org.example.acadify.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

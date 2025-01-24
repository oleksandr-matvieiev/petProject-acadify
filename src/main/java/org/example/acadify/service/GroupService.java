package org.example.acadify.service;

import org.example.acadify.DTOs.GroupDTO;
import org.example.acadify.exceptions.GroupNotFoundExc;
import org.example.acadify.mapper.GroupMapper;
import org.example.acadify.model.Group;
import org.example.acadify.model.Student;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.StudentRepository;
import org.springframework.stereotype.Service;


@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.groupMapper = groupMapper;
    }

    public GroupDTO createGroup(String groupName) {
        Group group = new Group();
        group.setName(groupName);
        groupRepository.save(group);
        return groupMapper.toDTO(group);
    }

//    public GroupDTO addStudentToGroup(String email, String groupName) {
//        Group group = groupRepository.findGroupByName(groupName)
//                .orElseThrow(GroupNotFoundExc::new);
//        Student student = studentRepository.findBy
//    }

}


package org.example.acadify.service;

import org.example.acadify.DTOs.GroupDTO;
import org.example.acadify.exceptions.GroupNotFoundExc;
import org.example.acadify.exceptions.IllegalArgumentExc;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.mapper.GroupMapper;
import org.example.acadify.model.Group;
import org.example.acadify.model.Student;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

    public GroupDTO changeStudentGroup(String email, String newGroupName) {
        Group group = groupRepository.findGroupByName(newGroupName)
                .orElseThrow(GroupNotFoundExc::new);
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(UserNotFoundExc::new);

        if (group == null || student == null) throw new IllegalArgumentExc();

        group.getStudents().add(student);
        student.setGroup(group);
        studentRepository.save(student);
        return groupMapper.toDTO(groupRepository.save(group));
    }

    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map(groupMapper::toDTO).collect(Collectors.toList()) ;
    }

}


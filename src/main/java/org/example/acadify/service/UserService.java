package org.example.acadify.service;

import lombok.RequiredArgsConstructor;
import org.example.acadify.DTOs.*;
import org.example.acadify.mapper.UserMapper;
import org.example.acadify.model.Group;
import org.example.acadify.model.Student;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.StudentRepository;
import org.example.acadify.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final UserMapper userMapper;

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToStudentResponseDTO)
                .collect(Collectors.toList());
    }


    public List<TeacherResponseDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();

        return teachers.stream().map(teacher -> {
            UserDTO userDTO = userMapper.toDTO(teacher);
            TeacherResponseDTO response = new TeacherResponseDTO();

            response.setEmail(userDTO.getEmail());
            response.setFirstName(userDTO.getFirstName());
            response.setLastName(userDTO.getLastName());

            return response;
        }).collect(Collectors.toList());
    }

    private StudentResponseDTO mapToStudentResponseDTO(Student student) {
        UserDTO userDTO =  userMapper.toDTO(student);
        StudentResponseDTO response = new StudentResponseDTO();
        response.setEmail(userDTO.getEmail());
        response.setFirstName(userDTO.getFirstName());
        response.setLastName(userDTO.getLastName());

        Group group = groupRepository.findGroupById(student.getGroup().getId());
        response.setGroupName(group != null ? group.getName() : "Unknown");

        return response;
    }


}

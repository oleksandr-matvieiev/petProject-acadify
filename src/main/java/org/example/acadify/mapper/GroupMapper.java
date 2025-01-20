package org.example.acadify.mapper;

import org.example.acadify.DTOs.GroupDTO;
import org.example.acadify.model.Group;
import org.example.acadify.model.Student;
import org.example.acadify.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    private final StudentRepository studentRepository;

    public GroupMapper(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public GroupDTO toDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();

        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());
        groupDTO.setStudentIds(mapStudentsToIds(group.getStudents()));

        return groupDTO;
    }

    public Group toEntity(GroupDTO groupDTO) {
        Group group = new Group();

        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        group.setStudents(mapIdsToStudents(groupDTO.getStudentIds()));

        return group;
    }

    private List<Long> mapStudentsToIds(List<Student> students) {
        if (students == null || students.isEmpty()) return List.of();

        return students.stream().map(Student::getId)
                .collect(Collectors.toList());
    }

    private List<Student> mapIdsToStudents(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return List.of();

        return studentRepository.findAllById(ids);
    }
}

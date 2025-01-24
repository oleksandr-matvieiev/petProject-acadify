package org.example.acadify.mapper;

import org.example.acadify.DTOs.GroupDTO;
import org.example.acadify.model.Group;
import org.example.acadify.model.Student;
import org.example.acadify.repository.StudentRepository;
import org.example.acadify.util.MappingUtils;
import org.springframework.stereotype.Component;

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
        groupDTO.setStudentIds(MappingUtils.mapEntitiesToIds(group.getStudents(), Student::getId));

        return groupDTO;
    }

    public Group toEntity(GroupDTO groupDTO) {
        Group group = new Group();

        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        group.setStudents(MappingUtils.mapIdsToEntities(groupDTO.getStudentIds(), studentRepository::findAllById));

        return group;
    }

}

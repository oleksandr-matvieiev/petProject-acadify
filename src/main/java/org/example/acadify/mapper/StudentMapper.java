package org.example.acadify.mapper;

import org.example.acadify.DTOs.StudentDTO;
import org.example.acadify.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "gradeIds", source = "grades.id")
    @Mapping(target = "submissionIds", source = "submissions.id")
    StudentDTO toDTO(Student student);

    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "grades.id", source = "gradeIds")
    @Mapping(target = "submissions.id", source = "submissionIds")
    Student toEntity(StudentDTO studentDTO);
}

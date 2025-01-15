package org.example.acadify.mapper;

import org.example.acadify.DTOs.TeacherDTO;
import org.example.acadify.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(target = "taskIds", source = "tasks.id")
    TeacherDTO toDTO(Teacher teacher);

    @Mapping(target = "tasks.id", source = "taskIds")
    Teacher toEntity(TeacherDTO teacherDTO);
}

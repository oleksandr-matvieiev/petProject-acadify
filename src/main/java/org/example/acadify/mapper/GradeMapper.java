package org.example.acadify.mapper;

import org.example.acadify.DTOs.GradeDTO;
import org.example.acadify.model.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "taskId", source = "task.id")
    GradeDTO toDTO(Grade grade);

    @Mapping(target = "student.id", source = "studentId")
    @Mapping(target = "task.id", source = "taskId")
    Grade toEntity(GradeDTO gradeDTO);
}

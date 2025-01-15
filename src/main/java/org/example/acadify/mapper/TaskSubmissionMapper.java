package org.example.acadify.mapper;

import org.example.acadify.DTOs.TaskSubmissionDTO;
import org.example.acadify.model.TaskSubmission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskSubmissionMapper {
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "taskId", source = "task.id")
    TaskSubmissionDTO toDTO(TaskSubmission submission);

    @Mapping(target = "student.id", source = "studentId")
    @Mapping(target = "task.id", source = "taskId")
    TaskSubmission toEntity(TaskSubmissionDTO submissionDTO);
}

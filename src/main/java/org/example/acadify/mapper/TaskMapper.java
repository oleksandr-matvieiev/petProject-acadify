package org.example.acadify.mapper;

import org.example.acadify.DTOs.TaskDTO;
import org.example.acadify.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "groupIds", source = "groups.id")
    TaskDTO toDTO(Task task);

    @Mapping(target = "teacher.id", source = "teacherId")
    @Mapping(target = "groups.id", source = "groupIds")
    Task toEntity(TaskDTO taskDTO);
}

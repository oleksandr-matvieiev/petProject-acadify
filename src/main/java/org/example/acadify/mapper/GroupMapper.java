package org.example.acadify.mapper;

import org.example.acadify.DTOs.GroupDTO;
import org.example.acadify.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mapping(target = "studentIds", source = "students.id")
    GroupDTO toDTO(Group group);

    @Mapping(target = "students.id", source = "studentIds")
    Group toEntity(GroupDTO groupDTO);
}

package org.example.acadify.mapper;

import org.example.acadify.DTOs.UserDTO;
import org.example.acadify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleIds", source = "roles.id")
    UserDTO toDTO(User user);

    @Mapping(target = "roles.id", source = "roleIds")
    User toEntity(UserDTO userDTO);
}

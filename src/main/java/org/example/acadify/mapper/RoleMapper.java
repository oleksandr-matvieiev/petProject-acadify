package org.example.acadify.mapper;

import org.example.acadify.DTOs.RoleDTO;
import org.example.acadify.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);

    Role toEntity(RoleDTO roleDTO);
}

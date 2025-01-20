package org.example.acadify.mapper;

import org.example.acadify.DTOs.RoleDTO;
import org.example.acadify.model.Role;

public class RoleMapper {
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getRoleName());

        return roleDTO;
    }

    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setRoleName(roleDTO.getRoleName());

        return role;
    }
}

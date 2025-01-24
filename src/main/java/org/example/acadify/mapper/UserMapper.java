package org.example.acadify.mapper;

import org.example.acadify.DTOs.UserDTO;
import org.example.acadify.model.Role;
import org.example.acadify.model.User;
import org.example.acadify.repository.RoleRepository;
import org.example.acadify.util.MappingUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleIds(MappingUtils.mapEntitiesToIds(user.getRoles(), Role::getId));

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setRoles(MappingUtils.mapIdsToEntities(userDTO.getRoleIds(), roleRepository::findAllById));

        return user;
    }
}

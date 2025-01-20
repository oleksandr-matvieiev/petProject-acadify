package org.example.acadify.mapper;

import org.example.acadify.DTOs.UserDTO;
import org.example.acadify.model.Role;
import org.example.acadify.model.User;
import org.example.acadify.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setRoleIds(mapRolesToIds(user.getRoles()));

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setRoles(mapIdsToRoles(userDTO.getRoleIds()));

        return user;
    }

    private List<Long> mapRolesToIds(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of();
        }
        return roles.stream().map(Role::getId)
                .collect(Collectors.toList());
    }

    private List<Role> mapIdsToRoles(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return roleRepository.findAllById(ids);
    }


}

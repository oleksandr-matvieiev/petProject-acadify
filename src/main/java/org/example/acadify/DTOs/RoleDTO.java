package org.example.acadify.DTOs;

import lombok.Data;
import org.example.acadify.Enums.RoleName;

@Data
public class RoleDTO {
    private Long id;

    private RoleName roleName;
}

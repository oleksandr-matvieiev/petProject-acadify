package org.example.acadify.DTOs;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String email;
    private String password;
}

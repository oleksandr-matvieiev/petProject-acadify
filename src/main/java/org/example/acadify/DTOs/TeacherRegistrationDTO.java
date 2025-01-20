package org.example.acadify.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class TeacherRegistrationDTO {

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;

}

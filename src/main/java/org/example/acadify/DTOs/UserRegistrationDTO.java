package org.example.acadify.DTOs;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final String groupName; //for students

}

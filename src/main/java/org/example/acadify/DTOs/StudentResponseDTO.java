package org.example.acadify.DTOs;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String groupName;
}

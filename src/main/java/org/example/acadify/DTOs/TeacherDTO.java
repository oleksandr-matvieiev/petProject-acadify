package org.example.acadify.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class TeacherDTO {
    private Long id;
    private List<Long> taskIds;
}

package org.example.acadify.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;

    private Long groupId;

    private List<Long> gradeIds;

    private List<Long> submissionIds;
}

package org.example.acadify.DTOs;

import lombok.Data;

@Data
public class GradeDTO {
    private Long id;

    private Double mark;

    private String feedback;

    private Long studentId;

    private Long taskId;
}

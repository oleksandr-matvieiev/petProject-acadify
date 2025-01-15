package org.example.acadify.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskSubmissionDTO {
    private Long id;

    private String content;

    private LocalDateTime submissionDate;

    private Long studentId;

    private Long taskId;
}

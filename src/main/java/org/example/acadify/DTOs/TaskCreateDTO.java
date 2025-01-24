package org.example.acadify.DTOs;

import lombok.Data;
import org.example.acadify.Enums.TaskType;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskCreateDTO {
    private String title;
    private String content;
    private String teacherEmail;
    private String subjectName;
    private List<String> groupNames;
    private LocalDateTime deadline;
    private TaskType type;
}

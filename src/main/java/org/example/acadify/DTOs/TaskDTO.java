package org.example.acadify.DTOs;

import lombok.Data;
import org.example.acadify.Enums.TaskType;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskDTO {

    private Long id;

    private String title;

    private String content;

    private TaskType type;

    private LocalDateTime deadline;

    private Boolean checked = false;

    private Long teacherId;

    private List<Long> groupIds;

}

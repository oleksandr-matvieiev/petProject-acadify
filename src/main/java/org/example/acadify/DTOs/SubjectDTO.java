package org.example.acadify.DTOs;

import jakarta.persistence.*;
import lombok.Data;
import org.example.acadify.model.Group;
import org.example.acadify.model.Student;
import org.example.acadify.model.Task;
import org.example.acadify.model.Teacher;

import java.util.List;

@Data
public class SubjectDTO {
    private Long id;

    private String name;

    private String description;

    private List<Long> teachersIds;

    private List<Long> studentsIds;

    private List<Long> tasksIds;

    private List<Long> groupsIds;

}

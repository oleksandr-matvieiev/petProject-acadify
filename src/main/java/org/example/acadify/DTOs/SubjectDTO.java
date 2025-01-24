package org.example.acadify.DTOs;

import lombok.Data;

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

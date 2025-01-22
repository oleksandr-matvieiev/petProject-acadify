package org.example.acadify.DTOs;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDTO extends UserDTO {
    private Long id;

    private Long groupId;

    private List<Long> gradeIds;

    private List<Long> submissionIds;
}

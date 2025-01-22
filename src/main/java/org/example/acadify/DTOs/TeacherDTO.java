package org.example.acadify.DTOs;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDTO extends UserDTO {
    private Long id;
    private List<Long> taskIds;
}

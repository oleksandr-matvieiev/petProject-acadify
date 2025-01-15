package org.example.acadify.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class GroupDTO {
    private Long id;

    private String name;

    private List<Long> studentIds;

}

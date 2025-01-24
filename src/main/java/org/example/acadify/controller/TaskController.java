package org.example.acadify.controller;

import org.example.acadify.DTOs.TaskCreateDTO;
import org.example.acadify.DTOs.TaskDTO;
import org.example.acadify.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    ResponseEntity<TaskDTO> createNewTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        TaskDTO taskDTO = taskService.createNewTask(taskCreateDTO);
        return new ResponseEntity<>(taskDTO, HttpStatus.CREATED);
    }
}

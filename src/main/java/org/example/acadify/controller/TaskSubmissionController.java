package org.example.acadify.controller;

import org.example.acadify.DTOs.TaskSubmissionDTO;
import org.example.acadify.service.TaskSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class TaskSubmissionController {
    private final TaskSubmissionService taskSubmissionService;

    public TaskSubmissionController(TaskSubmissionService taskSubmissionService) {
        this.taskSubmissionService = taskSubmissionService;
    }

    @PostMapping("/submit")
    public ResponseEntity<TaskSubmissionDTO> submitTask(
            @RequestParam Long studentId,
            @RequestParam Long taskId,
            @RequestParam String content) {
        TaskSubmissionDTO submissionDTO = taskSubmissionService.submitTask(studentId, taskId, content);
        return ResponseEntity.ok(submissionDTO);
    }

    @GetMapping("/by-task/{taskId}")
    public ResponseEntity<List<TaskSubmissionDTO>> getSubmissionsByTask(@PathVariable Long taskId) {
        List<TaskSubmissionDTO> submissions = taskSubmissionService.getSubmissionsByTask(taskId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<TaskSubmissionDTO>> getSubmissionsByStudent(@PathVariable Long studentId) {
        List<TaskSubmissionDTO> submissions = taskSubmissionService.getSubmissionsByStudent(studentId);
        return ResponseEntity.ok(submissions);
    }
}

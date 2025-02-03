package org.example.acadify.service;

import org.example.acadify.DTOs.TaskSubmissionDTO;
import org.example.acadify.mapper.TaskMapper;
import org.example.acadify.mapper.TaskSubmissionMapper;
import org.example.acadify.model.Student;
import org.example.acadify.model.Task;
import org.example.acadify.model.TaskSubmission;
import org.example.acadify.repository.StudentRepository;
import org.example.acadify.repository.TaskRepository;
import org.example.acadify.repository.TaskSubmissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskSubmissionService {
    private final TaskSubmissionRepository taskSubmissionRepository;
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;
    private final TaskSubmissionMapper taskSubmissionMapper;

    public TaskSubmissionService(TaskSubmissionRepository taskSubmissionRepository, TaskRepository taskRepository, StudentRepository studentRepository, TaskSubmissionMapper taskSubmissionMapper, TaskMapper taskMapper) {
        this.taskSubmissionRepository = taskSubmissionRepository;
        this.taskRepository = taskRepository;
        this.studentRepository = studentRepository;
        this.taskSubmissionMapper = taskSubmissionMapper;
    }

    public TaskSubmissionDTO submitTask(Long taskId, Long studentId, String content) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (LocalDateTime.now().isAfter(task.getDeadline())) {
            throw new RuntimeException("The deadline for this task has already passed");
        }
        TaskSubmission taskSubmission = new TaskSubmission();
        taskSubmission.setStudent(student);
        taskSubmission.setTask(task);
        taskSubmission.setContent(content);
        taskSubmission.setSubmissionDate(LocalDateTime.now());

        taskSubmissionRepository.save(taskSubmission);

        return taskSubmissionMapper.toDTO(taskSubmission);
    }

    public List<TaskSubmissionDTO> getSubmissionsByTask(Long taskId) {
        List<TaskSubmission> submissions = taskSubmissionRepository.findByTaskId(taskId);
        return submissions.stream().map(taskSubmissionMapper::toDTO).collect(Collectors.toList());
    }

    public List<TaskSubmissionDTO> getSubmissionsByStudent(Long studentId) {
        List<TaskSubmission> submissions = taskSubmissionRepository.findByStudentId(studentId);
        return submissions.stream().map(taskSubmissionMapper::toDTO).collect(Collectors.toList());
    }
}

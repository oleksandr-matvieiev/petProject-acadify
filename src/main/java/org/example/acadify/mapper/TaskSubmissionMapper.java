package org.example.acadify.mapper;

import org.example.acadify.DTOs.TaskSubmissionDTO;
import org.example.acadify.exceptions.TaskNotFoundExc;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.model.Student;
import org.example.acadify.model.Task;
import org.example.acadify.model.TaskSubmission;
import org.example.acadify.repository.StudentRepository;
import org.example.acadify.repository.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class TaskSubmissionMapper {
    private final StudentRepository studentRepository;
    private final TaskRepository taskRepository;

    public TaskSubmissionMapper(StudentRepository studentRepository, TaskRepository taskRepository) {
        this.studentRepository = studentRepository;
        this.taskRepository = taskRepository;
    }

    public TaskSubmissionDTO toDTO(TaskSubmission submission) {
        TaskSubmissionDTO taskSubmissionDTO = new TaskSubmissionDTO();

        taskSubmissionDTO.setId(submission.getId());
        taskSubmissionDTO.setContent(submission.getContent());
        taskSubmissionDTO.setSubmissionDate(submission.getSubmissionDate());
        taskSubmissionDTO.setStudentId(submission.getStudent().getId());
        taskSubmissionDTO.setTaskId(submission.getTask().getId());

        return taskSubmissionDTO;
    }

    public TaskSubmission toEntity(TaskSubmissionDTO submissionDTO) {
        TaskSubmission taskSubmission = new TaskSubmission();
        Student student = studentRepository.findById(submissionDTO.getStudentId())
                .orElseThrow(UserNotFoundExc::new);
        Task task = taskRepository.findById(submissionDTO.getTaskId())
                .orElseThrow(TaskNotFoundExc::new);

        taskSubmission.setId(submissionDTO.getId());
        taskSubmission.setContent(submissionDTO.getContent());
        taskSubmission.setSubmissionDate(submissionDTO.getSubmissionDate());
        taskSubmission.setStudent(student);
        taskSubmission.setTask(task);

        return taskSubmission;
    }
}

package org.example.acadify.mapper;

import org.example.acadify.DTOs.GradeDTO;
import org.example.acadify.exceptions.TaskNotFoundExc;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.model.Grade;
import org.example.acadify.model.Student;
import org.example.acadify.model.Task;
import org.example.acadify.repository.StudentRepository;
import org.example.acadify.repository.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {
    private final StudentRepository studentRepository;
    private final TaskRepository taskRepository;

    public GradeMapper(StudentRepository studentRepository, TaskRepository taskRepository) {
        this.studentRepository = studentRepository;
        this.taskRepository = taskRepository;
    }

    public GradeDTO toDTO(Grade grade) {
        GradeDTO gradeDTO = new GradeDTO();

        gradeDTO.setId(grade.getId());
        gradeDTO.setFeedback(grade.getFeedback());
        gradeDTO.setMark(grade.getMark());
        gradeDTO.setStudentId(grade.getStudent().getId());
        gradeDTO.setTaskId(grade.getTask().getId());

        return gradeDTO;
    }

    public Grade toEntity(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        Student student = studentRepository.findById(gradeDTO.getStudentId())
                .orElseThrow(UserNotFoundExc::new);
        Task task = taskRepository.findById(gradeDTO.getTaskId())
                .orElseThrow(TaskNotFoundExc::new);

        grade.setId(gradeDTO.getId());
        grade.setFeedback(gradeDTO.getFeedback());
        grade.setMark(gradeDTO.getMark());
        grade.setStudent(student);
        grade.setTask(task);

        return grade;
    }
}

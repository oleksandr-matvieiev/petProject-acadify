package org.example.acadify.mapper;

import org.example.acadify.DTOs.StudentDTO;
import org.example.acadify.exceptions.GroupNotFoundExc;
import org.example.acadify.model.Grade;
import org.example.acadify.model.Group;
import org.example.acadify.model.Student;
import org.example.acadify.model.TaskSubmission;
import org.example.acadify.repository.GradeRepository;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.TaskSubmissionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    private final GroupRepository groupRepository;
    private final GradeRepository gradeRepository;
    private final TaskSubmissionRepository taskSubmissionRepository;

    public StudentMapper(GroupRepository groupRepository, GradeRepository gradeRepository, TaskSubmissionRepository taskSubmissionRepository) {
        this.groupRepository = groupRepository;
        this.gradeRepository = gradeRepository;
        this.taskSubmissionRepository = taskSubmissionRepository;
    }

    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setGroupId(student.getGroup().getId());
        studentDTO.setGradeIds(mapGradesToIds(student.getGrades()));
        studentDTO.setSubmissionIds(mapTaskSubmissionsToIds(student.getSubmissions()));

        return studentDTO;
    }

    public Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        Group group = groupRepository.findById(studentDTO.getGroupId())
                .orElseThrow(GroupNotFoundExc::new);

        student.setId(studentDTO.getId());
        student.setGroup(group);
        student.setGrades(mapIdsToGrades(studentDTO.getGradeIds()));
        student.setSubmissions(mapIdsToTaskSubmissions(studentDTO.getSubmissionIds()));

        return student;
    }

    private List<Long> mapGradesToIds(List<Grade> grades) {
        if (grades == null || grades.isEmpty()) return List.of();

        return grades.stream().map(Grade::getId).collect(Collectors.toList());
    }

    private List<Grade> mapIdsToGrades(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return List.of();

        return gradeRepository.findAllById(ids);
    }

    private List<Long> mapTaskSubmissionsToIds(List<TaskSubmission> taskSubmissions) {
        if (taskSubmissions == null || taskSubmissions.isEmpty()) return List.of();

        return taskSubmissions.stream().map(TaskSubmission::getId).collect(Collectors.toList());
    }

    private List<TaskSubmission> mapIdsToTaskSubmissions(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return List.of();

        return taskSubmissionRepository.findAllById(ids);
    }


}

package org.example.acadify.mapper;

import org.example.acadify.DTOs.SubjectDTO;
import org.example.acadify.model.*;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.StudentRepository;
import org.example.acadify.repository.TaskRepository;
import org.example.acadify.repository.TeacherRepository;
import org.example.acadify.util.MappingUtils;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final TaskRepository taskRepository;

    public SubjectMapper(TeacherRepository teacherRepository, StudentRepository studentRepository, GroupRepository groupRepository, TaskRepository taskRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.taskRepository = taskRepository;
    }

    public SubjectDTO toDTO(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();

        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setDescription(subject.getDescription());
        subjectDTO.setTeachersIds(MappingUtils.mapEntitiesToIds(subject.getTeachers(), Teacher::getId));
        subjectDTO.setStudentsIds(MappingUtils.mapEntitiesToIds(subject.getStudents(), Student::getId));
        subjectDTO.setTasksIds(MappingUtils.mapEntitiesToIds(subject.getTasks(), Task::getId));
        subjectDTO.setGroupsIds(MappingUtils.mapEntitiesToIds(subject.getGroups(), Group::getId));

        return subjectDTO;
    }

    public Subject toEntity(SubjectDTO subjectDTO) {
        Subject subject = new Subject();

        subject.setId(subjectDTO.getId());
        subject.setName(subjectDTO.getName());
        subject.setDescription(subjectDTO.getDescription());
        subject.setTeachers(MappingUtils.mapIdsToEntities(subjectDTO.getTeachersIds(), teacherRepository::findAllById));
        subject.setStudents(MappingUtils.mapIdsToEntities(subjectDTO.getStudentsIds(), studentRepository::findAllById));
        subject.setTasks(MappingUtils.mapIdsToEntities(subjectDTO.getTasksIds(), taskRepository::findAllById));
        subject.setGroups(MappingUtils.mapIdsToEntities(subjectDTO.getGroupsIds(), groupRepository::findAllById));

        return subject;
    }
}

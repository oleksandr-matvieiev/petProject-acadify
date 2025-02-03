package org.example.acadify.service;

import org.example.acadify.DTOs.TaskCreateDTO;
import org.example.acadify.DTOs.TaskDTO;
import org.example.acadify.exceptions.IllegalArgumentExc;
import org.example.acadify.exceptions.SubjectNotFoundExc;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.mapper.TaskMapper;
import org.example.acadify.model.Group;
import org.example.acadify.model.Subject;
import org.example.acadify.model.Task;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.SubjectRepository;
import org.example.acadify.repository.TaskRepository;
import org.example.acadify.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository, GroupRepository groupRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO createNewTask(TaskCreateDTO taskCreateDTO) {
        Task task = new Task();
        Teacher teacher = teacherRepository.findByEmail(taskCreateDTO.getTeacherEmail())
                .orElseThrow(UserNotFoundExc::new);
        Subject subject = subjectRepository.findByName(taskCreateDTO.getSubjectName())
                .orElseThrow(SubjectNotFoundExc::new);
        if (!subject.getTeachers().contains(teacher))
            throw new IllegalArgumentExc("Teacher not found for this subject" + taskCreateDTO.getSubjectName());

        List<Group> groups = groupRepository.findAllByNameIn(taskCreateDTO.getGroupNames());

        if (groups.size() != taskCreateDTO.getGroupNames().size())
            throw new IllegalArgumentExc("Some groups not found");

        task.setTitle(taskCreateDTO.getTitle());
        task.setContent(taskCreateDTO.getContent());
        task.setType(taskCreateDTO.getType());
        task.setDeadline(taskCreateDTO.getDeadline());
        task.setSubject(subject);
        task.setTeacher(teacher);
        task.setGroups(groups);

        return taskMapper.toDTO(taskRepository.save(task));
    }

}
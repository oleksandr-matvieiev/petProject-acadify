package org.example.acadify.mapper;

import org.example.acadify.DTOs.TeacherDTO;
import org.example.acadify.model.Task;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {
    private final TaskRepository taskRepository;

    public TeacherMapper(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TeacherDTO toDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();

        teacherDTO.setId(teacher.getId());
        teacherDTO.setTaskIds(mapTasksToIds(teacher.getTasks()));

        return teacherDTO;
    }

    public Teacher toEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();

        teacher.setId(teacherDTO.getId());
        teacher.setTasks(mapIdsToTasks(teacherDTO.getTaskIds()));

        return teacher;
    }

    private List<Long> mapTasksToIds(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return null;

        return tasks.stream().map(Task::getId).collect(Collectors.toList());
    }

    private List<Task> mapIdsToTasks(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return null;

        return taskRepository.findAllById(ids);
    }
}

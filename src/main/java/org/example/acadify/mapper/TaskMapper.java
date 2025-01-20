package org.example.acadify.mapper;

import org.example.acadify.DTOs.TaskDTO;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.model.Group;
import org.example.acadify.model.Task;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;

    public TaskMapper(TeacherRepository teacherRepository, GroupRepository groupRepository) {
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }

    public TaskDTO toDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setContent(taskDTO.getContent());
        taskDTO.setType(task.getType());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setChecked(task.getChecked());
        taskDTO.setTeacherId(task.getTeacher().getId());
        taskDTO.setGroupIds(mapGroupsToIds(task.getGroups()));

        return taskDTO;
    }

    public Task toEntity(TaskDTO taskDTO) {
        Task task = new Task();
        Teacher teacher = teacherRepository.findById(taskDTO.getTeacherId())
                .orElseThrow(UserNotFoundExc::new);

        task.setId(task.getId());
        task.setTitle(taskDTO.getTitle());
        task.setContent(taskDTO.getContent());
        task.setType(taskDTO.getType());
        task.setDeadline(taskDTO.getDeadline());
        task.setChecked(taskDTO.getChecked());
        task.setTeacher(teacher);
        task.setGroups(mapIdsToGroups(taskDTO.getGroupIds()));

        return task;
    }

    private List<Long> mapGroupsToIds(List<Group> groups) {
        if (groups == null || groups.isEmpty()) return List.of();

        return groups.stream().map(Group::getId)
                .collect(Collectors.toList());
    }

    private List<Group> mapIdsToGroups(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return List.of();

        return groupRepository.findAllById(ids);
    }
}

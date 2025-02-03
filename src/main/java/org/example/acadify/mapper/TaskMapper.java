package org.example.acadify.mapper;

import org.example.acadify.DTOs.TaskDTO;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.model.Group;
import org.example.acadify.model.Task;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.TeacherRepository;
import org.example.acadify.util.MappingUtils;
import org.springframework.stereotype.Component;

@Component
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
        taskDTO.setContent(task.getContent());
        taskDTO.setType(task.getType());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setChecked(task.getChecked());
        taskDTO.setTeacherId(task.getTeacher().getId());
        taskDTO.setGroupIds(MappingUtils.mapEntitiesToIds(task.getGroups(), Group::getId));
        taskDTO.setSubjectId(task.getSubject().getId());

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
        task.setGroups(MappingUtils.mapIdsToEntities(taskDTO.getGroupIds(), groupRepository::findAllById));

        return task;
    }

}

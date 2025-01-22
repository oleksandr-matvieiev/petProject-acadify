package org.example.acadify.mapper;

import org.example.acadify.DTOs.TeacherDTO;
import org.example.acadify.model.Role;
import org.example.acadify.model.Task;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.RoleRepository;
import org.example.acadify.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;

    public TeacherMapper(TaskRepository taskRepository, RoleRepository roleRepository) {
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository;
    }

    public TeacherDTO toDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();

        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setRoleIds(mapRolesToIds(teacher.getRoles()));
        teacherDTO.setTaskIds(mapTasksToIds(teacher.getTasks()));

        return teacherDTO;
    }

    public Teacher toEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();

        teacher.setId(teacherDTO.getId());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setRoles(mapIdsToRoles(teacherDTO.getRoleIds()));
        teacher.setTasks(mapIdsToTasks(teacherDTO.getTaskIds()));

        return teacher;
    }

    private List<Long> mapRolesToIds(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of();
        }
        return roles.stream().map(Role::getId)
                .collect(Collectors.toList());
    }

    private List<Role> mapIdsToRoles(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return roleRepository.findAllById(ids);
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

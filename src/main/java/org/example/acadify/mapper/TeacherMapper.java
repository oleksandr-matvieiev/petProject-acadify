package org.example.acadify.mapper;

import org.example.acadify.DTOs.TeacherDTO;
import org.example.acadify.model.Role;
import org.example.acadify.model.Task;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.RoleRepository;
import org.example.acadify.repository.TaskRepository;
import org.example.acadify.util.MappingUtils;
import org.springframework.stereotype.Component;

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
        teacherDTO.setRoleIds(MappingUtils.mapEntitiesToIds(teacher.getRoles(), Role::getId));
        teacherDTO.setTaskIds(MappingUtils.mapEntitiesToIds(teacher.getTasks(), Task::getId));

        return teacherDTO;
    }

    public Teacher toEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();

        teacher.setId(teacherDTO.getId());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setRoles(MappingUtils.mapIdsToEntities(teacherDTO.getRoleIds(), roleRepository::findAllById));
        teacher.setTasks(MappingUtils.mapIdsToEntities(teacherDTO.getTaskIds(), taskRepository::findAllById));

        return teacher;
    }

}

package org.example.acadify.mapper;

import org.example.acadify.DTOs.StudentDTO;
import org.example.acadify.exceptions.GroupNotFoundExc;
import org.example.acadify.model.*;
import org.example.acadify.repository.*;
import org.example.acadify.util.MappingUtils;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;
    private final TaskSubmissionRepository taskSubmissionRepository;

    public StudentMapper(RoleRepository roleRepository, GroupRepository groupRepository, SubjectRepository subjectRepository, GradeRepository gradeRepository, TaskSubmissionRepository taskSubmissionRepository) {
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.gradeRepository = gradeRepository;
        this.taskSubmissionRepository = taskSubmissionRepository;
    }

    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setRoleIds(MappingUtils.mapEntitiesToIds(student.getRoles(), Role::getId));
        studentDTO.setGroupId(student.getGroup().getId());
        studentDTO.setSubjectIds(MappingUtils.mapEntitiesToIds(student.getSubjects(), Subject::getId));
        studentDTO.setGradeIds(MappingUtils.mapEntitiesToIds(student.getGrades(), Grade::getId));
        studentDTO.setSubmissionIds(MappingUtils.mapEntitiesToIds(student.getSubmissions(), TaskSubmission::getId));

        return studentDTO;
    }

    public Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        Group group = groupRepository.findById(studentDTO.getGroupId())
                .orElseThrow(GroupNotFoundExc::new);

        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setRoles(MappingUtils.mapIdsToEntities(studentDTO.getRoleIds(), roleRepository::findAllById));
        student.setGroup(group);
        student.setSubjects(MappingUtils.mapIdsToEntities(studentDTO.getSubjectIds(), subjectRepository::findAllById));
        student.setGrades(MappingUtils.mapIdsToEntities(studentDTO.getGradeIds(), gradeRepository::findAllById));
        student.setSubmissions(MappingUtils.mapIdsToEntities(studentDTO.getSubmissionIds(), taskSubmissionRepository::findAllById));

        return student;
    }

}

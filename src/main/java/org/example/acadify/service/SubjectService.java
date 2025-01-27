package org.example.acadify.service;

import org.example.acadify.DTOs.SubjectDTO;
import org.example.acadify.exceptions.GroupNotFoundExc;
import org.example.acadify.exceptions.IllegalArgumentExc;
import org.example.acadify.exceptions.SubjectNotFoundExc;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.mapper.SubjectMapper;
import org.example.acadify.model.*;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.StudentRepository;
import org.example.acadify.repository.SubjectRepository;
import org.example.acadify.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final AuthService authService;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, AuthService authService, GroupRepository groupRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.authService = authService;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.subjectMapper = subjectMapper;
    }

    public SubjectDTO createSubject(String name, String description, String groupName) {
        Subject subject = new Subject();
        Group group = groupRepository.findGroupByName(groupName).orElseThrow(GroupNotFoundExc::new);

        subject.setName(name);
        subject.getGroups().add(group);
        if (description != null) subject.setDescription(description);
        return subjectMapper.toDTO(subjectRepository.save(subject));
    }

    //TODO Combine addTeacherToSubject & addStudentToSubject
    public SubjectDTO addTeacherToSubject(String email, String subjectName) {
        if (!teacherRepository.existsByEmail(email) || !subjectRepository.existsByName(subjectName))
            throw new IllegalArgumentExc(subjectName);

        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(UserNotFoundExc::new);
        Subject subject = subjectRepository.findByName(subjectName)
                .orElseThrow(SubjectNotFoundExc::new);

        subject.getTeachers().add(teacher);
        teacher.getSubjects().add(subject);

        teacherRepository.save(teacher);
        return subjectMapper.toDTO(subjectRepository.save(subject));
    }

    public SubjectDTO addStudentToSubject(String email, String subjectName) {
        if (!studentRepository.existsByEmail(email) || !subjectRepository.existsByName(subjectName))
            throw new IllegalArgumentExc();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(UserNotFoundExc::new);
        Subject subject = subjectRepository.findByName(subjectName)
                .orElseThrow(SubjectNotFoundExc::new);

        subject.getStudents().add(student);
        student.getSubjects().add(subject);

        studentRepository.save(student);
        return subjectMapper.toDTO(subjectRepository.save(subject));
    }

    public List<SubjectDTO> getAvailableSubjectsForCurrentUser() {
        User currentUser = authService.getCurrentUserDetails();

        List<Subject> subjects;
        if (currentUser instanceof Teacher) {
            subjects = ((Teacher) currentUser).getSubjects();
        } else if (currentUser instanceof Student) {
            subjects = ((Student) currentUser).getSubjects();
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }

        return subjects.stream()
                .map(subjectMapper::toDTO)
                .toList();
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subjectMapper::toDTO)
                .toList();
    }

}

package org.example.acadify.service;

import org.example.acadify.DTOs.SubjectDTO;
import org.example.acadify.exceptions.GroupNotFoundExc;
import org.example.acadify.exceptions.IllegalArgumentExc;
import org.example.acadify.exceptions.SubjectNotFoundExc;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.mapper.SubjectMapper;
import org.example.acadify.model.Group;
import org.example.acadify.model.Student;
import org.example.acadify.model.Subject;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.StudentRepository;
import org.example.acadify.repository.SubjectRepository;
import org.example.acadify.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, GroupRepository groupRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
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
            throw new IllegalArgumentExc();

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
}

package org.example.acadify.service;

import org.example.acadify.DTOs.StudentDTO;
import org.example.acadify.DTOs.TeacherDTO;
import org.example.acadify.DTOs.UserLoginRequestDTO;
import org.example.acadify.DTOs.UserRegistrationDTO;
import org.example.acadify.Enums.RoleName;
import org.example.acadify.Security.JwtTokenProvider;
import org.example.acadify.exceptions.GroupNotFoundExc;
import org.example.acadify.exceptions.IllegalArgumentExc;
import org.example.acadify.exceptions.RoleNotFoundExc;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.mapper.StudentMapper;
import org.example.acadify.mapper.TeacherMapper;
import org.example.acadify.model.*;
import org.example.acadify.repository.GroupRepository;
import org.example.acadify.repository.RoleRepository;
import org.example.acadify.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, GroupRepository groupRepository, TeacherMapper teacherMapper, StudentMapper studentMapper, PasswordEncoder encoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.teacherMapper = teacherMapper;
        this.studentMapper = studentMapper;
        this.encoder = encoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public TeacherDTO registerTeacher(UserRegistrationDTO userRegistrationDTO) {
        if (!checkIfAllParamsIsNotBlack(userRegistrationDTO))
            throw new IllegalArgumentExc("You have bad params fpr registration!");


        Teacher teacher = new Teacher();
        teacher.setFirstName(userRegistrationDTO.getFirstName());
        teacher.setLastName(userRegistrationDTO.getLastName());
        teacher.setPassword(encoder.encode(userRegistrationDTO.getPassword()));
        teacher.setEmail(userRegistrationDTO.getEmail());

        Role teacherRole = roleRepository.findByRoleName(RoleName.TEACHER).
                orElseThrow(RoleNotFoundExc::new);
        teacher.getRoles().add(teacherRole);

        return teacherMapper.toDTO(userRepository.save(teacher));
    }

    public String login(UserLoginRequestDTO userLoginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDTO.getEmail(),
                        userLoginRequestDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(userLoginRequestDTO.getEmail())
                .orElseThrow(UserNotFoundExc::new);

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .toList();
        return jwtTokenProvider.generateToken(user.getEmail(), roles);
    }

    public StudentDTO registerStudent(UserRegistrationDTO registerDTO) {
        if (!checkIfAllParamsIsNotBlack(registerDTO))
            throw new IllegalArgumentExc("You have bad params fpr registration!");

        Student student = new Student();
        Role studentRole = roleRepository.findByRoleName(RoleName.STUDENT)
                .orElseThrow(RoleNotFoundExc::new);
        Group studentGroup = groupRepository.findGroupByName(registerDTO.getGroupName())
                .orElseThrow(GroupNotFoundExc::new);

        student.setFirstName(registerDTO.getFirstName());
        student.setLastName(registerDTO.getLastName());
        student.setEmail(registerDTO.getEmail());
        student.getRoles().add(studentRole);
        student.setGroup(studentGroup);
        student.setPassword(encoder.encode(registerDTO.getPassword()));

        return studentMapper.toDTO(userRepository.save(student));
    }

    private boolean checkIfAllParamsIsNotBlack(UserRegistrationDTO registrationDTO) {
        if (registrationDTO.getFirstName().isBlank()) {
            return false;

        }
        if (registrationDTO.getLastName().isBlank()) {
            return false;

        }
        if (registrationDTO.getEmail().isBlank()) {
            return false;

        }
        return !userRepository.existsByEmail(registrationDTO.getEmail());

    }
}

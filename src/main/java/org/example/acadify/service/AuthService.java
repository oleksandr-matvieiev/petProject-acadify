package org.example.acadify.service;

import org.example.acadify.DTOs.TeacherDTO;
import org.example.acadify.DTOs.TeacherRegistrationDTO;
import org.example.acadify.Enums.RoleName;
import org.example.acadify.exceptions.IllegalArgumentExc;
import org.example.acadify.exceptions.RoleNotFoundExc;
import org.example.acadify.mapper.TeacherMapper;
import org.example.acadify.model.Role;
import org.example.acadify.model.Teacher;
import org.example.acadify.repository.RoleRepository;
import org.example.acadify.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder encoder;
//    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, TeacherMapper teacherMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.teacherMapper = teacherMapper;
//        this.jwtTokenProvider = jwtTokenProvider;
        this.encoder = encoder;
    }

    public TeacherDTO registerTeacher(TeacherRegistrationDTO teacherRegistrationDTO) {
        if (teacherRegistrationDTO.getFirstName().isBlank()) {
            throw new IllegalArgumentExc("First name cannot be blank.");
        }
        if (teacherRegistrationDTO.getLastName().isBlank()) {
            throw new IllegalArgumentExc("Last name cannot be blank.");
        }
        if (teacherRegistrationDTO.getEmail().isBlank()) {
            throw new IllegalArgumentExc("First name cannot be blank.");
        }
        if (userRepository.existsByEmail(teacherRegistrationDTO.getEmail())) {
            throw new IllegalArgumentExc("Email is already registered.");
        }

        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherRegistrationDTO.getFirstName());
        teacher.setLastName(teacherRegistrationDTO.getLastName());
        teacher.setPassword(encoder.encode(teacherRegistrationDTO.getPassword()));
        teacher.setEmail(teacherRegistrationDTO.getEmail());

        Role teacherRole = roleRepository.findByRoleName(RoleName.TEACHER).
                orElseThrow(RoleNotFoundExc::new);
        teacher.getRoles().add(teacherRole);

        return teacherMapper.toDTO(userRepository.save(teacher));
    }
}

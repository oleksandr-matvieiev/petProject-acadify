package org.example.acadify.service;

import org.example.acadify.DTOs.TeacherDTO;
import org.example.acadify.DTOs.TeacherLoginDTO;
import org.example.acadify.DTOs.TeacherRegistrationDTO;
import org.example.acadify.Enums.RoleName;
import org.example.acadify.Security.JwtTokenProvider;
import org.example.acadify.exceptions.IllegalArgumentExc;
import org.example.acadify.exceptions.RoleNotFoundExc;
import org.example.acadify.exceptions.UserNotFoundExc;
import org.example.acadify.mapper.TeacherMapper;
import org.example.acadify.model.Role;
import org.example.acadify.model.Teacher;
import org.example.acadify.model.User;
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
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, TeacherMapper teacherMapper, PasswordEncoder encoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.teacherMapper = teacherMapper;
        this.encoder = encoder;
        this.jwtTokenProvider = jwtTokenProvider;
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
    public String login(TeacherLoginDTO teacherLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        teacherLoginDTO.getEmail(),
                        teacherLoginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(teacherLoginDTO.getEmail())
                .orElseThrow(UserNotFoundExc::new);

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .toList();
        return jwtTokenProvider.generateToken(user.getEmail(), roles);
    }
}

package org.example.acadify.controller;

import org.example.acadify.DTOs.TeacherDTO;
import org.example.acadify.DTOs.UserLoginRequestDTO;
import org.example.acadify.DTOs.TeacherRegistrationDTO;
import org.example.acadify.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<TeacherDTO> registerTeacher(@RequestBody TeacherRegistrationDTO teacherRegistrationDTO) {
        TeacherDTO teacherDTO = authService.registerTeacher(teacherRegistrationDTO);
        return new ResponseEntity<>(teacherDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login/teacher")
    public ResponseEntity<?> loginTeacher(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        String token = authService.login(userLoginRequestDTO);
        return ResponseEntity.ok(token);
    }
}

package org.example.acadify.controller;

import org.example.acadify.DTOs.SubjectDTO;
import org.example.acadify.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/create")
    public ResponseEntity<SubjectDTO> createNewSubject(@RequestParam String name,
                                                       @RequestParam(required = false) String description,
                                                       @RequestParam String groupName) {
        SubjectDTO subjectDTO = subjectService.createSubject(name, description, groupName);
        return new ResponseEntity<>(subjectDTO, HttpStatus.CREATED);
    }

    @PostMapping("/add/teacher")
    public ResponseEntity<SubjectDTO> addNewTeacherToSubject(@RequestParam String email, @RequestParam String subjectName) {
        SubjectDTO subjectDTO = subjectService.addTeacherToSubject(email, subjectName);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @PostMapping("/add/student")
    public ResponseEntity<SubjectDTO> addNewStudentToSubject(@RequestParam String email, @RequestParam String subjectName) {
        SubjectDTO subjectDTO = subjectService.addStudentToSubject(email, subjectName);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }
}

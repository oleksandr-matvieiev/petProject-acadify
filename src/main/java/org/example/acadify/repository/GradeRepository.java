package org.example.acadify.repository;

import org.example.acadify.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByTaskId(Long taskId);

}

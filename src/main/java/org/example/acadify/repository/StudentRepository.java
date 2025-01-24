package org.example.acadify.repository;

import org.example.acadify.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends BaseUserRepository<Student> {
    List<Student> findByGroupId(Long groupId);
}

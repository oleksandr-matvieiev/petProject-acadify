package org.example.acadify.repository;

import org.example.acadify.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends BaseUserRepository<Teacher> {
}

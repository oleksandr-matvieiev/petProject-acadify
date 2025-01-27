package org.example.acadify.repository;

import org.example.acadify.model.Subject;
import org.example.acadify.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByName(String name);

    Optional<Subject> findByName(String name);

    List<Subject> findAllByTeachersContains(List<Teacher> teachers);
}

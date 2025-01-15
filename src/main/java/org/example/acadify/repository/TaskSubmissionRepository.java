package org.example.acadify.repository;

import org.example.acadify.model.TaskSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskSubmissionRepository extends JpaRepository<TaskSubmission, Long> {
    List<TaskSubmission> findByStudentId(Long studentId);
    List<TaskSubmission> findByTaskId(Long taskId);
}

package org.example.acadify.repository;

import org.example.acadify.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTeacherId(Long teacherId);
    List<Task> findByGroupsId(Long groupId);
}

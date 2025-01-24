package org.example.acadify.repository;

import org.example.acadify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseUserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

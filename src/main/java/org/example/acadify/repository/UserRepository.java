package org.example.acadify.repository;

import org.example.acadify.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseUserRepository<User> {
}

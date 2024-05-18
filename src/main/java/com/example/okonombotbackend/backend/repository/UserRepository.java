package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}

package com.example.okonombotbackend.backend.repository;

import com.example.okonombotbackend.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findUsersByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}

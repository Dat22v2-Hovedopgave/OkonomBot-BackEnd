package com.example.okonombotbackend.security.repository;

import com.example.okonombotbackend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserWithRolesRepository extends JpaRepository<User,String> {
    Boolean existsByEmail(String email);
}

package com.example.okonombotbackend.security.repository;

import com.example.okonombotbackend.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserWithRolesRepository extends JpaRepository<Users,String> {
    Boolean existsByEmail(String email);
}

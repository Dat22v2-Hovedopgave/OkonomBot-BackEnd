package com.example.okonombotbackend.repository;

import com.example.okonombotbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserId(int userId);
}

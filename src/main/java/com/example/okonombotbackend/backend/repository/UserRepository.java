package com.example.okonombotbackend.backend.repository;

<<<<<<< Updated upstream
import com.example.okonombotbackend.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserId(int userId);
}
=======

import com.example.okonombotbackend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findUserById(int userId);
}
>>>>>>> Stashed changes

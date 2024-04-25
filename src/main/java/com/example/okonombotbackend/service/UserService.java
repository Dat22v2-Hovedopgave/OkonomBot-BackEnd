package com.example.okonombotbackend.service;

import com.example.okonombotbackend.dto.UserDTO;
import com.example.okonombotbackend.entity.User;
import com.example.okonombotbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserDTO getUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToUser(user);
    }

    private UserDTO convertToUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPasswordHash(user.getPasswordHash());
        return userDTO;
    }
}

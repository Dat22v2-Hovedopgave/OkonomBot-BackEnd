package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.UserRequest;
import com.example.okonombotbackend.backend.dto.UserResponse;
import com.example.okonombotbackend.security.entity.User;
import com.example.okonombotbackend.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Boolean> saveUser(UserRequest userRequest) throws Exception {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new Exception("User with this email already in use.");
        }
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new Exception("Username already in use.");
        }
        User user = requestToUser(userRequest);
        userRepository.save(user);

        return ResponseEntity.ok(true);
    }

    public UserResponse getUserById(String username) {
        User user;
        try {
            user = userRepository.findUserByUsername(username);
        } catch(Error error){
            throw new RuntimeException("User not found");
        }
        return entityToResponse(user);
    }

    public User requestToUser(UserRequest body) {
        User user = new User();
        user.setPassword(body.getPassword());
        user.setUsername(body.getUsername());
        user.setEmail(body.getEmail());
        return user;
    }

    private UserResponse entityToResponse(User user) {
        UserResponse responseTmp = new UserResponse();
        responseTmp.setUsername(user.getUsername());
        responseTmp.setEmail(user.getEmail());
        responseTmp.setCreated(user.getCreated());
        responseTmp.setLastEdited(user.getEdited());
        return responseTmp;
    }
}

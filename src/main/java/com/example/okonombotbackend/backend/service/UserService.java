package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.UserRequest;
import com.example.okonombotbackend.backend.dto.UserResponse;
import com.example.okonombotbackend.security.entity.Users;
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
        Users users = requestToUser(userRequest);
        userRepository.save(users);

        return ResponseEntity.ok(true);
    }

    public UserResponse getUserById(String username) {
        Users users;
        try {
            users = userRepository.findUsersByUsername(username);
        } catch(Error error){
            throw new RuntimeException("User not found");
        }
        return entityToResponse(users);
    }

    public Users requestToUser(UserRequest body) {
        Users users = new Users();
        users.setPassword(body.getPassword());
        users.setUsername(body.getUsername());
        users.setEmail(body.getEmail());
        return users;
    }

    private UserResponse entityToResponse(Users users) {
        UserResponse responseTmp = new UserResponse();
        responseTmp.setUsername(users.getUsername());
        responseTmp.setEmail(users.getEmail());
        responseTmp.setCreated(users.getCreated());
        responseTmp.setLastEdited(users.getEdited());
        return responseTmp;
    }
}

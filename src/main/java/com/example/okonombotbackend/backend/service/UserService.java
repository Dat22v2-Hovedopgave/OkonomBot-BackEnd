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
    UserRepository userRepository;

    public ResponseEntity<Boolean> saveUser(UserRequest userRequest) throws Exception {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new Exception("User with this email already in use.");
        }
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new Exception("Username already in use.");
        }
        User user = userRepository.findUserByUsername(userRequest.getUsername());

        userRepository.save(user);

        return ResponseEntity.ok(true);
    }

    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        return entityToResponse(user);
    }

/*    public Users requestToUser(UserRequest body) { //TODO: DELETE?
        Users users = new Users();
        users.setPassword(body.getPassword());
        users.setUsername(body.getUsername());
        users.setEmail(body.getEmail());
        return users;
    }*/

    private UserResponse entityToResponse(User user) {
        UserResponse responseTmp = new UserResponse();
        responseTmp.setUsername(user.getUsername());
        responseTmp.setEmail(user.getEmail());
        responseTmp.setCreated(user.getCreated());
        responseTmp.setLastEdited(user.getEdited());
        return responseTmp;
    }
}

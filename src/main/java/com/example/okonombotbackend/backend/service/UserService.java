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
        // Tjek om email allerede er i brug
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new Exception("User with this email already in use.");
        }
        // Tjek om brugernavn allerede er i brug
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new Exception("Username already in use.");
        }

        // Opret ny bruger fra userRequest
        User user = new User(userRequest);

        // Gem bruger i databasen
        userRepository.save(user);

        return ResponseEntity.ok(true); // Returner succes-respons
    }

    public UserResponse getUserByUsername(String username) {
        // Find bruger baseret på brugernavn
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found"); // Kast fejl hvis bruger ikke findes
        }
        return entityToResponse(user); // Returner UserResponse
    }

    private UserResponse entityToResponse(User user) {
        UserResponse responseTmp = new UserResponse();
        responseTmp.setUsername(user.getUsername()); // Sæt brugernavn
        responseTmp.setEmail(user.getEmail()); // Sæt email
        responseTmp.setCreated(user.getCreated()); // Sæt oprettelsesdato
        responseTmp.setLastEdited(user.getEdited()); // Sæt sidste redigeringsdato
        return responseTmp; // Returner UserResponse objekt
    }

}

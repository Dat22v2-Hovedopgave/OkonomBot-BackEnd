package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.dto.UserRequest;
import com.example.okonombotbackend.backend.dto.UserResponse;
import com.example.okonombotbackend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody UserRequest userRequest) throws Exception {
        return userService.saveUser(userRequest);
    }

    @GetMapping("/{username}")
    public UserResponse getUserById(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}

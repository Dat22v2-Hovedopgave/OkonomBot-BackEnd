package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.dto.UserDTO;
import com.example.okonombotbackend.security.entity.User;
import com.example.okonombotbackend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{username}")
    public UserDTO getUserById(@PathVariable String username) {
        return userService.getUserById(username);
    }

}
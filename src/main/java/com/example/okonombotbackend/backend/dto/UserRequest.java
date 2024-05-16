package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.security.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String username;
    private String email;
    private String password;

    public static UserRequest requestFromUser(Users users) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(users.getUsername());
        userRequest.setEmail(users.getEmail());
        userRequest.setPassword(users.getPassword());
        return userRequest;
    }
}

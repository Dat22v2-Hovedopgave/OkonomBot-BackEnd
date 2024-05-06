package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.security.entity.User;
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

    public static UserRequest requestFromUser(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(user.getUsername());
        userRequest.setEmail(user.getEmail());
        userRequest.setPassword(user.getPassword());
        return userRequest;
    }
}

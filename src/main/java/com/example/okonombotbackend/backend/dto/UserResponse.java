package com.example.okonombotbackend.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String username;
    private String email;
    private LocalDateTime created;
    private LocalDateTime lastEdited;

    public UserResponse(String username, String email, LocalDateTime created, LocalDateTime lastEdited) {
        this.username = username;
        this.email = email;
        this.created = created;
        this.lastEdited = lastEdited;
    }
}

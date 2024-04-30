package com.example.okonombotbackend.backend.dto.chatGPT.chatGptRequest;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class Message {
    public String role;
    public String content;
}

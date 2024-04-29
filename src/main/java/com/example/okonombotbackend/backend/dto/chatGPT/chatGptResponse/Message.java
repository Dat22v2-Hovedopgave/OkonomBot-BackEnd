package com.example.okonombotbackend.backend.dto.chatGPT.chatGptResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Message {
    public String role;
    public String content;
}

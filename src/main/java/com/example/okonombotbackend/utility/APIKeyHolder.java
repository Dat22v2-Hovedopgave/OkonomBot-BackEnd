package com.example.okonombotbackend.utility;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class APIKeyHolder {
    @Value("${CHATGPT_API_KEY}")
    @Getter
    private String chatGPTAPIKey;

}
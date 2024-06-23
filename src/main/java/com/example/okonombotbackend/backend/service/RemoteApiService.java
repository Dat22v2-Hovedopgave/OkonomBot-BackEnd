package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.chatGPT.AI_DTO;
import com.example.okonombotbackend.backend.utility.APIKeyHolder;
import com.example.okonombotbackend.backend.dto.chatGPT.chatGptRequest.ChatGPTRequest;
import com.example.okonombotbackend.backend.dto.chatGPT.chatGptRequest.Message;
import com.example.okonombotbackend.backend.dto.chatGPT.chatGptResponse.ChatGPTResponse;
import com.example.okonombotbackend.backend.utility.MonoApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoteApiService {

    @Autowired
    APIKeyHolder apiKeyHolder;

    public AI_DTO responseFromAI(AI_DTO ai_dto) {
        // Extract message from AI_DTO
        String finalPrompt = ai_dto.getMessage();

        // Create a ChatGPT request
        ChatGPTRequest gptRequest = new ChatGPTRequest();
        Message userMessage = new Message("user", finalPrompt); // Create a user message
        gptRequest.addMessage(userMessage); // Add message to the request

        // Get response from ChatGPT API
        ChatGPTResponse chatGPTResponse = getChatGPTResponse(gptRequest);

        // Create a new AI_DTO with the response message
        AI_DTO tmpAi_dto = new AI_DTO();
        tmpAi_dto.setMessage(chatGPTResponse.getChoices().get(0).getMessage().getContent());

        // Return the new AI_DTO
        return tmpAi_dto;
    }

    public ChatGPTResponse getChatGPTResponse(ChatGPTRequest body) {
        String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions"; // OpenAI API URL

        // Call the API and return the response
        return MonoApiCaller.callPostApi(ChatGPTResponse.class, OPENAI_API_URL, body, headersWithAuthorization(apiKeyHolder.getChatGPTAPIKey())).block();
    }

    private Map<String, String> headersWithAuthorization(String bearerToken) {
        // Create headers with Content-Type and Authorization
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + bearerToken);

        // Return the headers map
        return headers;
    }



}

package com.example.okonombotbackend.service;

import com.example.okonombotbackend.dto.chatGPT.AI_DTO;
import com.example.okonombotbackend.dto.chatGPT.chatGptRequest.ChatGPTRequest;
import com.example.okonombotbackend.dto.chatGPT.chatGptRequest.Message;
import com.example.okonombotbackend.dto.chatGPT.chatGptResponse.ChatGPTResponse;
import com.example.okonombotbackend.utility.APIKeyHolder;
import com.example.okonombotbackend.utility.MonoApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoteApiService {

    @Autowired
    APIKeyHolder apiKeyHolder;

    public AI_DTO responseFromAI(AI_DTO ai_dto){

        //TODO: Være sikke på, at frontend sender én lang string.
        String finalPrompt = ai_dto.getMessage();

        //Now we put the information into chatGPT.
        ChatGPTRequest gptRequest = new ChatGPTRequest();
        Message userMessage = new Message("user",finalPrompt);
        gptRequest.addMessage(userMessage);
        ChatGPTResponse chatGPTResponse = getChatGPTResponse(gptRequest);


        AI_DTO tmpAi_dto = new AI_DTO();
        tmpAi_dto.setMessage(chatGPTResponse.getChoices().get(0).getMessage().getContent());

        return tmpAi_dto;
    }

    public ChatGPTResponse getChatGPTResponse(ChatGPTRequest body){
        String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
        return MonoApiCaller.callPostApi(ChatGPTResponse.class, OPENAI_API_URL,
                body, headersWithAuthorization(apiKeyHolder.getChatGPTAPIKey())).block();
    }

    private Map<String, String> headersWithAuthorization(String bearerToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","Bearer " + bearerToken);
        return headers;
    }



}

package com.example.okonombotbackend.backend.dto.chatGPT.chatGptRequest;

import lombok.*;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ChatGPTRequest {
    public String model = "gpt-3.5-turbo";
    public ArrayList<Message> messages;
    public int max_tokens = 400;


    public void addMessage(Message newMessage){
        if(messages == null){
            messages = new ArrayList<>();
            final String content = "You are an economic expert, in Danish analyze the data being sent, and give recommendations. The minus signifies a spending and positive numbers are income. Only give recommendations if the spending/income seems excessive. Also give general advice on economics when necessary. Max 300 tokens.";
            Message system = new Message("system", content);
            messages.add(system);
        }
        messages.add(newMessage);
    }
}




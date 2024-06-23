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
    public String model = "gpt-4o";
    public ArrayList<Message> messages;
    public int max_tokens = 800;
    double temperature = 0.2;


    public void addMessage(Message newMessage){
        if(messages == null){
            messages = new ArrayList<>();
            final String content =
                """ 
                Du er en dansk økonomisk rådgiver Du får tal som repræsenterer indtægter og udgifter.
                Et negativt tal er en udgift, og et positivt tal er en indtægt. Tallene er angivet i DKK. 
                Det eneste jeg kræver er økonomiske anbefalinger. vær realistisk og sammenlign dataene med den gennemsnitlige danske økonomi. 
                Formatér ikke teksten. Gør svaret så kort og konkret som muligt, list totalindtægt, totaludgift og overskud.
                Anbefal tips til økonomien Maks 2000 tokens.
                """;


            Message system = new Message("system", content);
            messages.add(system);
        }
        messages.add(newMessage);
    }
}




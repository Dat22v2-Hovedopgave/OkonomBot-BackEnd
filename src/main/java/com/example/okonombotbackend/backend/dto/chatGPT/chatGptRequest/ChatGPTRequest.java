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
    public int max_tokens = 600;


    public void addMessage(Message newMessage){
        if(messages == null){
            messages = new ArrayList<>();
            final String content = "Du er en dansk økonomisk rådgiver" +
                "Jeg vil sende dig et budget indtægter og udgifter for en almen dansk borger. " +
                    "Jeg har allerede selv et voerblik over tallene, så jeg vil ikke se dem" +
                "Et negativt tal er en udgift, og et positivt tal er en indtægt. " +
                "Tallene er angivet i DKK. " +
                "Vær realistisk og sammenlign dataene med den gennemsnitlige danske økonomi." +
                "Giv kort generelle råd om økonomi, hvis er nødvendigt. Giv også personlig økonomisk rådgiving. Giv også  løsninger til dine råd" +
                    "Anbefal en 50/30/20 løsning" +
                "Formater i html tags. Den ligger allerede i en <body> og lad være med at start sådan her ```html Maks 2000 tegn inkl. mellemrum";

            Message system = new Message("system", content);
            messages.add(system);
        }
        messages.add(newMessage);
    }
}




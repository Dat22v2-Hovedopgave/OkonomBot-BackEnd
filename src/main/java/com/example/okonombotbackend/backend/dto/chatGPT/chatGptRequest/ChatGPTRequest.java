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
    public int max_tokens = 400;


    public void addMessage(Message newMessage){
        if(messages == null){
            messages = new ArrayList<>();
            final String content = "Du er en dansk økonomisk ekspert der skal rådgive en almen dansk borger " +
                "Jeg vil sende dig mit budget for indtægter og udgifter. " +
                "Et negativt tal er en udgift, og et positivt tal er en indtægt. " +
                "Tallene er angivet i DKK. " +
                "Vær realistisk og sammenlign dataene med den gennemsnitlige danske økonomi." +
                "Start med at vurdere personens indtægter, og sig hvilken samfundsklasse han ligger." +
                "Giv kort generelle råd om økonomi, hvis er nødvendigt. Giv også personlig økonomisk rådgiving" +
                "Response skal være uden tekst formatering. Maks 300 tokens";

            Message system = new Message("system", content);
            messages.add(system);
        }
        messages.add(newMessage);
    }
}




package com.example.okonombotbackend.api;

import com.example.okonombotbackend.dto.UserDTO;
import com.example.okonombotbackend.dto.chatGPT.AI_DTO;
import com.example.okonombotbackend.entity.User;
import com.example.okonombotbackend.service.RemoteApiService;
import com.example.okonombotbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {
    @Autowired
    private RemoteApiService remoteApiService;

    @PostMapping("/createUser")
    public AI_DTO createUser(@RequestBody AI_DTO ai_dto) {
        //TODO: Få denne metode til at fungere.
        return remoteApiService.responseFromAI(ai_dto);
    }
}

package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.dto.chatGPT.AI_DTO;
import com.example.okonombotbackend.backend.service.RemoteApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin
public class AIController {

    @Autowired
    private RemoteApiService remoteApiService;

    @PostMapping("/")
    public AI_DTO createUser(@RequestBody AI_DTO ai_dto) {
        return remoteApiService.responseFromAI(ai_dto);
    }


}

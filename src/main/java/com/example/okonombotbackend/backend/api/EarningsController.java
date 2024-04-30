package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.dto.EarningRequest;
import com.example.okonombotbackend.backend.dto.EarningResponse;
import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.service.EarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/earnings")
public class EarningsController {
    @Autowired
    private EarningsService earningsService;

    @PostMapping("/addEarning")
    public ResponseEntity createEarning(@RequestBody EarningRequest body) {
        return ResponseEntity.ok(earningsService.addEarning(body));
    }

    @GetMapping("/user/{userId}")
    public List<EarningResponse> getEarningsByUserId(@PathVariable int userId) {
        return earningsService.getEarningsByUserId(userId);
    }
}

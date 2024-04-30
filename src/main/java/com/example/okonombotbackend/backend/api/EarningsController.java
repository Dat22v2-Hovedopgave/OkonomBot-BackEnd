package com.example.okonombotbackend.api;

import com.example.okonombotbackend.dto.EarningRequest;
import com.example.okonombotbackend.entity.Earning;
import com.example.okonombotbackend.service.EarningsService;
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
    public List<Earning> getEarningsByUserId(@PathVariable int userId) {
        return null;
    }
}

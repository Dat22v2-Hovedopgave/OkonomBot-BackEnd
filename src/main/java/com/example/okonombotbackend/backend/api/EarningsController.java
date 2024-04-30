package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.service.EarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/earnings")
public class EarningsController {
    @Autowired
    private EarningsService earningsService;

    @PostMapping("/")
    public Earning createEarning(@RequestBody Earning earning) {
        return null;
    }

    @GetMapping("/user/{userId}")
    public List<Earning> getEarningsByUserId(@PathVariable int userId) {
        return null;
    }
}

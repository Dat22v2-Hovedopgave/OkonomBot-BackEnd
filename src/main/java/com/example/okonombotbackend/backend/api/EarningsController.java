package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.dto.earning.EarningDetailedResponse;
import com.example.okonombotbackend.backend.dto.earning.EarningRequest;
import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.service.EarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/earnings")
public class EarningsController {
    @Autowired
    private EarningsService earningsService;

    @PostMapping("/addEarning")
    public ResponseEntity createEarning(@RequestBody EarningRequest body) {
        return ResponseEntity.ok(earningsService.addEarning(body));
    }

    @GetMapping("/user/{username}")
    public List<EarningDetailedResponse> getEarningsByUsername(@PathVariable String username) {
        return earningsService.getEarningsByUsername(username);
    }

    @PostMapping("/addEarnings")
    public ResponseEntity<?> addEarnings(@RequestBody List<EarningRequest> body) {
        System.out.println(body);
        List<Earning> earnings = earningsService.addEarnings(body);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully added earnings! " + earnings.size() + " earnings added");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{earningId}")
    public ResponseEntity<?> deleteEarning(@PathVariable int earningId) {
        earningsService.deleteEarning(earningId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully deleted earning with id " + earningId);
        return ResponseEntity.ok(response);
    }
}

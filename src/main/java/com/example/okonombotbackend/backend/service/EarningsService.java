package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.earning.EarningDetailedResponse;
import com.example.okonombotbackend.backend.dto.earning.EarningRequest;
import com.example.okonombotbackend.backend.dto.earning.EarningResponse;
import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.repository.EarningsRepository;
import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import com.example.okonombotbackend.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class EarningsService {
    @Autowired
    private EarningsRepository earningsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubcategoryRepository subcategoryRepository;

    public EarningResponse addEarning(EarningRequest body) {
        Earning earning = new Earning();
        earning.setUser(userRepository.findUserByUsername(body.getUsername()));

        //if user already has the same subcategory, then update the amount to the existing subcategory

            if (earningsRepository.existsByUserAndSubcategoryId(userRepository.findUserByUsername(body.getUsername()), body.getSubcategoryId())) {
                Earning existingEarning = earningsRepository.findByUserAndSubcategoryId(userRepository.findUserByUsername(body.getUsername()), body.getSubcategoryId());
                existingEarning.setAmount(body.getAmount());
                return new EarningResponse(earningsRepository.save(existingEarning));

        }

        earning.setSubcategory(subcategoryRepository.findSubcategoryById(body.getSubcategoryId()));
        earning.setAmount(body.getAmount());
        return new EarningResponse(earningsRepository.save(earning));
    }

    public List<EarningDetailedResponse> getEarningsByUsername(String username) {
        List<Earning> allEarnings = earningsRepository.findAll();

        return allEarnings.stream()
                .filter(earning -> earning.getUser().getUsername().equalsIgnoreCase(username))
                .map(EarningDetailedResponse::new)
                .toList();
    }

    public List<Earning> addEarnings(List<EarningRequest> body) {
        System.out.println(body);

        List<Earning> responses = new ArrayList<>();

        for (EarningRequest earningRequest : body) {
            Earning earning = new Earning();
            earning.setUser(userRepository.findUserByUsername(earningRequest.getUsername()));

            if (earningsRepository.existsByUserAndSubcategoryId(userRepository.findUserByUsername(earningRequest.getUsername()), earningRequest.getSubcategoryId())) {
                Earning existingEarning = earningsRepository.findByUserAndSubcategoryId(userRepository.findUserByUsername(earningRequest.getUsername()), earningRequest.getSubcategoryId());
                earning.setId(existingEarning.getId());
                earning.setSubcategory(existingEarning.getSubcategory());
                earning.setAmount(earningRequest.getAmount());
                existingEarning.setAmount(earningRequest.getAmount());
                earningsRepository.save(existingEarning);
            } else {
                earning.setSubcategory(subcategoryRepository.findSubcategoryById(earningRequest.getSubcategoryId()));
                earning.setAmount(earningRequest.getAmount());
                earningsRepository.save(earning);
            }
            responses.add(earning);
        }
        return responses;
    }

    public void deleteEarning(int earningId) {
        earningsRepository.deleteById(earningId);
    }
}
package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.EarningRequest;
import com.example.okonombotbackend.backend.dto.EarningResponse;
import com.example.okonombotbackend.backend.entity.Earning;
import com.example.okonombotbackend.backend.repository.EarningsRepository;
import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import com.example.okonombotbackend.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
        earning.setUser(userRepository.findUserByUserId(body.getUserId()));
        earning.setSubcategory(subcategoryRepository.findSubcategoryBySubcategoryId(body.getSubcategoryId()));
        earning.setAmount(body.getAmount());
        return new EarningResponse(earningsRepository.save(earning));
    }

}
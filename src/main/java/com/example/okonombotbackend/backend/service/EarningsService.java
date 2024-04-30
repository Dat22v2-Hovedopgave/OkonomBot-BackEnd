package com.example.okonombotbackend.service;

import com.example.okonombotbackend.dto.EarningRequest;
import com.example.okonombotbackend.dto.EarningResponse;
import com.example.okonombotbackend.dto.ExpenseResponse;
import com.example.okonombotbackend.entity.Earning;
import com.example.okonombotbackend.entity.Subcategory;
import com.example.okonombotbackend.repository.EarningsRepository;
import com.example.okonombotbackend.repository.SubcategoryRepository;
import com.example.okonombotbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
        earning.setUser(userRepository.findUserByUserId(body.getUserId()));
        earning.setSubcategory(subcategoryRepository.findSubcategoryBySubcategoryId(body.getSubcategoryId()));
        earning.setAmount(body.getAmount());
        return new EarningResponse(earningsRepository.save(earning));
    }

}
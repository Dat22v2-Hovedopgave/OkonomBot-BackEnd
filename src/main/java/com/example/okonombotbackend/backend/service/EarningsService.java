package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.repository.EarningsRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EarningsService {
    @Autowired
    private EarningsRepository earningsRepository;

}

package com.example.okonombotbackend.service;

import com.example.okonombotbackend.entity.Earning;
import com.example.okonombotbackend.repository.EarningsRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class EarningsService {
    @Autowired
    private EarningsRepository earningsRepository;

}

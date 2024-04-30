package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.repository.ExpensesRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ExpensesService {
    @Autowired
    private ExpensesRepository expensesRepository;

}

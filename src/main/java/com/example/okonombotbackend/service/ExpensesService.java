package com.example.okonombotbackend.service;

import com.example.okonombotbackend.entity.Expense;
import com.example.okonombotbackend.repository.ExpensesRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ExpensesService {
    @Autowired
    private ExpensesRepository expensesRepository;

}

package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.ExpenseRequest;
import com.example.okonombotbackend.backend.dto.ExpenseResponse;
import com.example.okonombotbackend.backend.entity.Expense;
import com.example.okonombotbackend.backend.repository.ExpensesRepository;
import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import com.example.okonombotbackend.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ExpensesService {
    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public ExpenseResponse addExpense(ExpenseRequest body) {
        Expense expense = new Expense();
        expense.setUser(userRepository.findUserByUserId(body.getUserId()));
        expense.setSubcategory(subcategoryRepository.findSubcategoryBySubcategoryId(body.getSubcategoryId()));
        expense.setAmount(body.getAmount());

        return new ExpenseResponse(expensesRepository.save(expense));

    }
}

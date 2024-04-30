package com.example.okonombotbackend.service;

import com.example.okonombotbackend.dto.ExpenseRequest;
import com.example.okonombotbackend.dto.ExpenseResponse;
import com.example.okonombotbackend.entity.Expense;
import com.example.okonombotbackend.entity.Subcategory;
import com.example.okonombotbackend.repository.ExpensesRepository;
import com.example.okonombotbackend.repository.SubcategoryRepository;
import com.example.okonombotbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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

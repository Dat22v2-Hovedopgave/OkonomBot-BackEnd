package com.example.okonombotbackend.backend.service;

import com.example.okonombotbackend.backend.dto.expense.ExpenseDetailedResponse;
import com.example.okonombotbackend.backend.dto.expense.ExpenseRequest;
import com.example.okonombotbackend.backend.dto.expense.ExpenseResponse;
import com.example.okonombotbackend.backend.entity.Expense;
import com.example.okonombotbackend.backend.repository.ExpensesRepository;
import com.example.okonombotbackend.backend.repository.SubcategoryRepository;
import com.example.okonombotbackend.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
        expense.setUsers(userRepository.findUsersByUsername(body.getUsername()));
        expense.setSubcategory(subcategoryRepository.findSubcategoryById(body.getSubcategoryId()));
        expense.setAmount(body.getAmount());

        return new ExpenseResponse(expensesRepository.save(expense));

    }

    public List<Expense> addExpenses(List<ExpenseRequest> body) {
        List<Expense> responses = new ArrayList<>();

        for (ExpenseRequest expenseRequest : body) {
            Expense expense = new Expense();
            expense.setUsers(userRepository.findUsersByUsername(expenseRequest.getUsername()));

            if (expensesRepository.existsByUsersAndSubcategoryId(userRepository.findUsersByUsername(expenseRequest.getUsername()), expenseRequest.getSubcategoryId())) {
                Expense existingExpense = expensesRepository.findByUsersAndSubcategoryId(userRepository.findUsersByUsername(expenseRequest.getUsername()), expenseRequest.getSubcategoryId());
                expense.setId(existingExpense.getId());
                expense.setSubcategory(existingExpense.getSubcategory());
                expense.setAmount(expenseRequest.getAmount());
                existingExpense.setAmount(expenseRequest.getAmount());
                expensesRepository.save(existingExpense);
            } else {
                expense.setSubcategory(subcategoryRepository.findSubcategoryById(expenseRequest.getSubcategoryId()));
                expense.setAmount(expenseRequest.getAmount());
                expensesRepository.save(expense);
            }
            responses.add(expense);
        }
        return responses;
    }

    public void deleteExpense(int expenseId) {
        expensesRepository.deleteById(expenseId);
    }

    public List<ExpenseDetailedResponse> getExpensesByUsername(String username) {
        List<Expense> allExpenses = expensesRepository.findAll();

        return allExpenses.stream()
            .filter(expense -> expense.getUsers().getUsername().equalsIgnoreCase(username))
            .map(ExpenseDetailedResponse::new)
            .toList();    }
}

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
        expense.setUser(userRepository.findUserByUsername(body.getUsername())); // Find og sæt bruger
        expense.setSubcategory(subcategoryRepository.findSubcategoryById(body.getSubcategoryId())); // Find og sæt underkategori
        expense.setAmount(body.getAmount()); // Sæt beløb

        return new ExpenseResponse(expensesRepository.save(expense)); // Gem og returner ExpenseResponse
    }

    public List<Expense> addExpenses(List<ExpenseRequest> body) {
        List<Expense> responses = new ArrayList<>();

        for (ExpenseRequest expenseRequest : body) {
            Expense expense = new Expense();
            expense.setUser(userRepository.findUserByUsername(expenseRequest.getUsername())); // Find og sæt bruger

            // Tjek om udgift allerede eksisterer
            if (expensesRepository.existsByUserAndSubcategoryId(userRepository.findUserByUsername(expenseRequest.getUsername()), expenseRequest.getSubcategoryId())) {
                Expense existingExpense = expensesRepository.findByUserAndSubcategoryId(userRepository.findUserByUsername(expenseRequest.getUsername()), expenseRequest.getSubcategoryId());
                expense.setId(existingExpense.getId()); // Sæt eksisterende ID
                expense.setSubcategory(existingExpense.getSubcategory()); // Sæt eksisterende underkategori
                expense.setAmount(expenseRequest.getAmount()); // Opdater beløb
                existingExpense.setAmount(expenseRequest.getAmount()); // Opdater eksisterende beløb
                expensesRepository.save(existingExpense); // Gem opdateret udgift
            } else {
                expense.setSubcategory(subcategoryRepository.findSubcategoryById(expenseRequest.getSubcategoryId())); // Find og sæt underkategori
                expense.setAmount(expenseRequest.getAmount()); // Sæt beløb
                expensesRepository.save(expense); // Gem ny udgift
            }
            responses.add(expense); // Tilføj til responsliste
        }
        return responses; // Returner liste af udgifter
    }

    public void deleteExpense(int expenseId) {
        expensesRepository.deleteById(expenseId); // Slet udgift efter ID
    }

    public List<ExpenseDetailedResponse> getExpensesByUsername(String username) {
        List<Expense> allExpenses = expensesRepository.findAll(); // Hent alle udgifter

        // Filtrer og map udgifter til detaljeret respons
        return allExpenses.stream()
                .filter(expense -> expense.getUser().getUsername().equalsIgnoreCase(username)) // Filtrer efter brugernavn
                .map(ExpenseDetailedResponse::new) // Map til detaljeret respons
                .toList();
    }

}

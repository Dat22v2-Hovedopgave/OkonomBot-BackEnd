package com.example.okonombotbackend.api;

import com.example.okonombotbackend.dto.ExpenseRequest;
import com.example.okonombotbackend.entity.Expense;
import com.example.okonombotbackend.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;

    @PostMapping("/addExpense")
    public ResponseEntity createExpense(@RequestBody ExpenseRequest body) {
    return ResponseEntity.ok(expensesService.addExpense(body));
    }
    

    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUserId(@PathVariable int userId) {
        return null;
    }
}

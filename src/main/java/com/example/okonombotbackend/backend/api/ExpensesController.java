package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.entity.Expense;
import com.example.okonombotbackend.backend.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;

    @PostMapping("/")
    public Expense createExpense(@RequestBody Expense expense) {
        return null;
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUserId(@PathVariable int userId) {
        return null;
    }
}

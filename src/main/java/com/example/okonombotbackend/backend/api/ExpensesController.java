package com.example.okonombotbackend.backend.api;

import com.example.okonombotbackend.backend.dto.expense.ExpenseDetailedResponse;
import com.example.okonombotbackend.backend.dto.expense.ExpenseRequest;
import com.example.okonombotbackend.backend.entity.Expense;
import com.example.okonombotbackend.backend.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;

    @PostMapping("/addExpense")
    public ResponseEntity createExpense(@RequestBody ExpenseRequest body) {
    return ResponseEntity.ok(expensesService.addExpense(body));
    }
    @GetMapping("/user/{username}")
    public List<ExpenseDetailedResponse> getExpensesByUserId(@PathVariable String username) {
        return expensesService.getExpensesByUsername(username);
    }

    @PostMapping("/addExpenses")
    public ResponseEntity<?> addExpenses(@RequestBody List<ExpenseRequest> body){
        System.out.println(body);
        List<Expense> expenses = expensesService.addExpenses(body);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully added earnings! " + expenses.size() + " expenses added");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable int expenseId) {
        expensesService.deleteExpense(expenseId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully deleted expense with id " + expenseId);
        return ResponseEntity.ok(response);
    }
}

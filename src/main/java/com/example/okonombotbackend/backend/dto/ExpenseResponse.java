package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Expense;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ExpenseResponse {
    private int expenseId;
    private String username;
    private int subcategoryId;
    private Double amount;
    private Date date;

    public ExpenseResponse(Expense expense) {
        this.expenseId = expense.getId();
        this.username = expense.getUser().getUsername();
        this.subcategoryId = expense.getSubcategory().getId();
        this.amount = expense.getAmount();
        this.date = expense.getDate();
    }
}

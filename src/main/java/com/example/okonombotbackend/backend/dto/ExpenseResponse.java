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
    private int userId;
    private int subcategoryId;
    private Double amount;
    private Date date;

    public ExpenseResponse(Expense expense) {
        this.expenseId = expense.getExpenseId();
        this.userId = expense.getUser().getUserId();
        this.subcategoryId = expense.getSubcategory().getSubcategoryId();
        this.amount = expense.getAmount();
        this.date = expense.getDate();
    }
}

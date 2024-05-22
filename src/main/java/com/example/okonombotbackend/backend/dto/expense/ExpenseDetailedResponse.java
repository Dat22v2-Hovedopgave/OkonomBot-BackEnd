package com.example.okonombotbackend.backend.dto.expense;

import com.example.okonombotbackend.backend.entity.Expense;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
public class ExpenseDetailedResponse {
    private int expenseId;
    private String username;
    private int subcategoryId;
    private Double amount;
    private Date date;
    private String subcategoryName;
    private String categoryName;
    private int categoryId;

    public ExpenseDetailedResponse(Expense expense) {
        this.expenseId = expense.getId();
        this.username = expense.getUser().getUsername();
        this.subcategoryId = expense.getSubcategory().getId();
        this.amount = expense.getAmount();
        this.date = expense.getDate();
        this.subcategoryName = expense.getSubcategory().getName();
        this.categoryName = expense.getSubcategory().getCategory().getName();
        this.categoryId = expense.getSubcategory().getCategory().getId();
    }

}

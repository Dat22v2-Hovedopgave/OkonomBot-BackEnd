package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Expense;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ExpenseDTO {
    private int expenseId;
    private int userId;
    private int subcategoryId;
    private Double amount;
    private Date date;

    public static ExpenseDTO getExpenseDTO(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setExpenseId(expense.getExpenseId());
        expenseDTO.setUserId(expense.getUser().getUserId());
        expenseDTO.setSubcategoryId(expense.getSubcategory().getSubcategoryId());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setDate(expense.getDate());
        return expenseDTO;
    }
}

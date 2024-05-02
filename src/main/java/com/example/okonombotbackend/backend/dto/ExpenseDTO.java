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
    private int id;
    private String username;
    private int subcategoryId;
    private Double amount;
    private Date date;

    public static ExpenseDTO getExpenseDTO(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(expense.getId());
        expenseDTO.setUsername(expense.getUser().getUsername());
        expenseDTO.setSubcategoryId(expense.getSubcategory().getId());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setDate(expense.getDate());
        return expenseDTO;
    }
}

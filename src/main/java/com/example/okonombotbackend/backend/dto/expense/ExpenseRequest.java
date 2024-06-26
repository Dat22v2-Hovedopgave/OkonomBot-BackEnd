package com.example.okonombotbackend.backend.dto.expense;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExpenseRequest {
    private String username;
    private int subcategoryId;
    private Double amount;
}

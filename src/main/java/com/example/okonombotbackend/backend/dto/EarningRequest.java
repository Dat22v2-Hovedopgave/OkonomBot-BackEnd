package com.example.okonombotbackend.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class EarningRequest {
    private int userId;
    private int subcategoryId;
    private Double amount;
}

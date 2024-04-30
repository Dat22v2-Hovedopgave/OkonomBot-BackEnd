package com.example.okonombotbackend.dto;

import com.example.okonombotbackend.entity.Earning;

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

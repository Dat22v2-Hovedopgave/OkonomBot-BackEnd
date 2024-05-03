package com.example.okonombotbackend.backend.dto.earning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EarningRequest {
    private int userId;
    private int subcategoryId;
    private Double amount;
}

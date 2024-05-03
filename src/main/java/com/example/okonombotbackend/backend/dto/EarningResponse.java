package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Earning;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class EarningResponse {
    private int earningId;
    private String username;
    private int subcategoryId;
    private Double amount;
    private Date date;

    public EarningResponse(Earning earning) {
        this.earningId = earning.getId();
        this.username = earning.getUser().getUsername();
        this.subcategoryId = earning.getSubcategory().getId();
        this.amount = earning.getAmount();
        this.date = earning.getDate();
    }
}

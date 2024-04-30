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
    private int userId;
    private int subcategoryId;
    private Double amount;
    private Date date;

    public EarningResponse(Earning earning) {
        this.earningId = earning.getEarningId();
        this.userId = earning.getUser().getUserId();
        this.subcategoryId = earning.getSubcategory().getSubcategoryId();
        this.amount = earning.getAmount();
        this.date = earning.getDate();
    }
}

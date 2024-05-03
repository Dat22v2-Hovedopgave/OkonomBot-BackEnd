package com.example.okonombotbackend.backend.dto.earning;

import com.example.okonombotbackend.backend.entity.Earning;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
public class EarningDetailedResponse {
    private int earningId;
    private int userId;
    private String username;
    private int subcategoryId;
    private Double amount;
    private Date date;
    private String subcategoryName;
    private String categoryName;

    public EarningDetailedResponse(Earning earning) {
        this.earningId = earning.getId();
        this.userId = earning.getUser().getId();
        this.username = earning.getUser().getUsername();
        this.subcategoryId = earning.getSubcategory().getId();
        this.amount = earning.getAmount();
        this.date = earning.getDate();
        this.subcategoryName = earning.getSubcategory().getName();
        this.categoryName = earning.getSubcategory().getCategory().getName();
    }

}

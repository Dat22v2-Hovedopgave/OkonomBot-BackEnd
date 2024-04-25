package com.example.okonombotbackend.dto;

import com.example.okonombotbackend.entity.Earning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class EarningDTO {
    private int earningId;
    private int userId;
    private int subcategoryId;
    private Double amount;
    private Date date;

    public static EarningDTO getEarningDTO(Earning earning) {
        EarningDTO earningDTO = new EarningDTO();
        earningDTO.setEarningId(earning.getEarningId());
        earningDTO.setUserId(earning.getUser().getUserId());
        earningDTO.setSubcategoryId(earning.getSubcategory().getSubcategoryId()); 
        earningDTO.setAmount(earning.getAmount());
        earningDTO.setDate(earning.getDate());
        return earningDTO;
    }
}

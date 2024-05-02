package com.example.okonombotbackend.backend.dto;

import com.example.okonombotbackend.backend.entity.Earning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class EarningDTO {
    private int earningId;
    private String username;
    private int subcategoryId;
    private Double amount;
    private Date date;

    public static EarningDTO getEarningDTO(Earning earning) {
        EarningDTO earningDTO = new EarningDTO();
        earningDTO.setEarningId(earning.getId());
        earningDTO.setUsername(earning.getUser().getUsername());
        earningDTO.setSubcategoryId(earning.getSubcategory().getId());
        earningDTO.setAmount(earning.getAmount());
        earningDTO.setDate(earning.getDate());
        return earningDTO;
    }
}

package com.example.okonombotbackend.backend.dto.earning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EarningListRequest {
    private List<EarningRequest> earnings;
}

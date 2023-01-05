package com.jjj.cashlesstips.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipsPaymentIntentParams {   
    private String currency;
    private BigDecimal amount;
}

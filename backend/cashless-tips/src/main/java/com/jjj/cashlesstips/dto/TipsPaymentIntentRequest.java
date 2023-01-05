package com.jjj.cashlesstips.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TipsPaymentIntentRequest {
    @NotEmpty(message = "currency can not be empty")
    private String currency;
    @NotNull(message = "amount can not be empty")
    private BigDecimal amount;
}

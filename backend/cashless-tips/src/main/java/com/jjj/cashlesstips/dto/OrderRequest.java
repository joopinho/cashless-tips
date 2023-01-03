package com.jjj.cashlesstips.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    private String externalId;
}

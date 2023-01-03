package com.jjj.cashlesstips.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class OrderResponse{

    private Long id;
    
    private BigDecimal amount;

    private String linkId;

    private UserResponse creator;
}

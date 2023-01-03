package com.jjj.cashlesstips.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipsPaymentIntentResponse {
    
    private String id;
    
    private String clientSecret;

}
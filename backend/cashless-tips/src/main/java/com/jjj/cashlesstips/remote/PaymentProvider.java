package com.jjj.cashlesstips.remote;

import com.jjj.cashlesstips.dto.TipsPaymentIntent;
import com.jjj.cashlesstips.dto.TipsPaymentIntentParams;

public interface PaymentProvider {
    
    TipsPaymentIntent createPaymentIntent(TipsPaymentIntentParams paymentParams, String integrationId);
}

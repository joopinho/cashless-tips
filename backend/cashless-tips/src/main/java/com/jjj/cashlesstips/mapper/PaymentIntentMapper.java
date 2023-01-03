package com.jjj.cashlesstips.mapper;

import org.mapstruct.Mapper;

import com.jjj.cashlesstips.dto.TipsPaymentIntent;
import com.jjj.cashlesstips.dto.TipsPaymentIntentParams;
import com.jjj.cashlesstips.dto.TipsPaymentIntentRequest;
import com.jjj.cashlesstips.dto.TipsPaymentIntentResponse;
import com.stripe.model.PaymentIntent;

@Mapper(componentModel = "spring")
public interface PaymentIntentMapper {
  
    TipsPaymentIntentResponse mapTipsPaymentIntentToPaymentIntentResponse(TipsPaymentIntent pi);
    TipsPaymentIntentParams mapPaymentIntentRequestToPaymentIntentParams(TipsPaymentIntentRequest piRequest);
    TipsPaymentIntent mapStripePaymentIntentToTipsPaymentIntent(PaymentIntent pi);


}

package com.jjj.cashlesstips.remote.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jjj.cashlesstips.dto.TipsPaymentIntent;
import com.jjj.cashlesstips.dto.TipsPaymentIntentParams;
import com.jjj.cashlesstips.exception.PaymentProviderException;
import com.jjj.cashlesstips.mapper.PaymentIntentMapper;
import com.jjj.cashlesstips.remote.PaymentProvider;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StripePaymentProvider  implements PaymentProvider{

    @Value("${application.stripe.api.secretKey}")
    private String secretKey;

    private final PaymentIntentMapper paymentIntentMapper;

    @Override
    public TipsPaymentIntent createPaymentIntent(TipsPaymentIntentParams paymentParams, String integrationId) {

        Stripe.apiKey = this.secretKey;

        PaymentIntentCreateParams params =
            PaymentIntentCreateParams
                .builder()
                .setAmount(paymentParams.getAmount().longValue())
                .setCurrency(paymentParams.getCurrency())
                .addPaymentMethodType("card")
                .putMetadata("order_id", integrationId)
                .build();

        PaymentIntent paymentIntent = null;
            try {
                paymentIntent  = PaymentIntent.create(params);
            } catch (StripeException e) {
                throw new PaymentProviderException("Error creating payment intent with orderId: " 
                                                    + integrationId
                                                    + ".\nMessage was: "
                                                    + e.getMessage());
            }
                
        
        return paymentIntentMapper.mapStripePaymentIntentToTipsPaymentIntent(paymentIntent);
    }
    
}

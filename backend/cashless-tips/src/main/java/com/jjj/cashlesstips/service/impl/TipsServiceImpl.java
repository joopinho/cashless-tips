package com.jjj.cashlesstips.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jjj.cashlesstips.dao.entity.Tips;
import com.jjj.cashlesstips.dto.TipsPaymentIntent;
import com.jjj.cashlesstips.dto.TipsPaymentIntentParams;
import com.jjj.cashlesstips.remote.PaymentProvider;
import com.jjj.cashlesstips.repository.TipsRepository;
import com.jjj.cashlesstips.service.TipsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipsServiceImpl implements TipsService{

    private final PaymentProvider paymentProvider;
    private final TipsRepository tipsRepository;

    
    @Override
    public TipsPaymentIntent createPaymentIntent(TipsPaymentIntentParams paymentParams, Long orderId) {
        return paymentProvider.createPaymentIntent(paymentParams, orderId.toString());
    }

    @Override
    @Transactional
    public Tips createTips(Tips tips) {
        return tipsRepository.save(tips);
    }

    @Override
    @Transactional
    public TipsPaymentIntent createTipsPaymentIntent(TipsPaymentIntentParams paymentParams, Long orderId) {
        TipsPaymentIntent paymentIntent = createPaymentIntent(paymentParams, orderId);

        Tips tips = new Tips(paymentParams.getAmount(), orderId, paymentIntent.getId());
        createTips(tips);
        
        return paymentIntent;
    }
    
}

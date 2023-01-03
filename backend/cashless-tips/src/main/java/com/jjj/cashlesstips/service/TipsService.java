package com.jjj.cashlesstips.service;

import com.jjj.cashlesstips.dao.entity.Tips;
import com.jjj.cashlesstips.dto.TipsPaymentIntent;
import com.jjj.cashlesstips.dto.TipsPaymentIntentParams;

public interface TipsService {
   public TipsPaymentIntent createPaymentIntent(TipsPaymentIntentParams paymentParams, Long orderId);
   public Tips createTips(Tips tips);
   public TipsPaymentIntent createTipsPaymentIntent(TipsPaymentIntentParams paymentParams, Long orderId);
}

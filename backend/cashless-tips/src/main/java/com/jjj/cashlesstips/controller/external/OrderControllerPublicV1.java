package com.jjj.cashlesstips.controller.external;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjj.cashlesstips.dto.OrderResponse;
import com.jjj.cashlesstips.dto.TipsPaymentIntentRequest;
import com.jjj.cashlesstips.dto.TipsPaymentIntentResponse;
import com.jjj.cashlesstips.mapper.OrderMapper;
import com.jjj.cashlesstips.mapper.PaymentIntentMapper;
import com.jjj.cashlesstips.service.OrderService;
import com.jjj.cashlesstips.service.TipsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderControllerPublicV1 {  

    private final TipsService tipsService;
    private final OrderService orderService;
    private final PaymentIntentMapper paymentIntentMapper;
    private final OrderMapper orderMapper;

    @PostMapping("/{orderId}/tips")
    public TipsPaymentIntentResponse createTips(@PathVariable Long orderId,
                             @RequestBody @Valid TipsPaymentIntentRequest request) {
        return paymentIntentMapper.mapTipsPaymentIntentToPaymentIntentResponse(
            tipsService.createTipsPaymentIntent(
                paymentIntentMapper.mapPaymentIntentRequestToPaymentIntentParams(request), orderId));
    }
    
    @GetMapping("/{linkId}")
    public OrderResponse getOrder(@PathVariable String linkId){
        return orderMapper.mapEntityToOrderResponse(
                    orderService.getOrderByLinkId(linkId));
    }

}

package com.jjj.cashlesstips.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.jjj.cashlesstips.dao.entity.Order;
import com.jjj.cashlesstips.dto.OrderRequest;
import com.jjj.cashlesstips.dto.OrderResponse;

@Mapper(componentModel = "spring", 
        uses = UserMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {

    Order mapOrderRequestToEntity(OrderRequest request);
    
    OrderResponse mapEntityToOrderResponse(Order order);
}

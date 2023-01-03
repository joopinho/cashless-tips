package com.jjj.cashlesstips.service;

import org.springframework.data.domain.Page;

import com.jjj.cashlesstips.dao.entity.Order;

public interface OrderService {
    public Order createOrder(Order order);
    public Order getOrderByLinkId(String linkId);
    public Order getOrderById(Long id);
    public Page<Order> getUserOrders(Long userId, int page, int pageSize, String sortBy, String sortDir);
}

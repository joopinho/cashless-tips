package com.jjj.cashlesstips.service.impl;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjj.cashlesstips.dao.entity.Order;
import com.jjj.cashlesstips.repository.OrderRepository;
import com.jjj.cashlesstips.service.OrderService;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiseImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Order createOrder(Order order) {
        orderRepository.findByExternalId(order.getExternalId()).ifPresent(
            value -> {
                throw new IllegalArgumentException("Order with externalId: " 
                                                    + order.getExternalId() 
                                                    + " already exists");
            }
        );

        order.setLinkId(RandomStringUtils.random(8, true, false).toLowerCase());

        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderByLinkId(String linkId) {
        return orderRepository.findByLinkId(linkId.toLowerCase()).orElseThrow(
            () -> new NoSuchElementException("Order is not found by link: " + linkId));
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("Order is not found by id: " + id));
    }

    @Override
    public Page<Order> getUserOrders(Long userId, int page, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return orderRepository.findByUserId(userId, pageable);
    }
    
}

package com.jjj.cashlesstips.controller.internal;

import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jjj.cashlesstips.config.OrderConfiguration;
import com.jjj.cashlesstips.dao.entity.Order;
import com.jjj.cashlesstips.dto.OrderPagebleResponse;
import com.jjj.cashlesstips.dto.OrderRequest;
import com.jjj.cashlesstips.dto.OrderResponse;
import com.jjj.cashlesstips.mapper.OrderMapper;
import com.jjj.cashlesstips.service.OrderService;
import com.jjj.cashlesstips.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/order")
@RequiredArgsConstructor
public class OrderControllerV1 {  

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderConfiguration orderConfig;
    private final UserService userService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody @Valid OrderRequest request) {
        return orderMapper.mapEntityToOrderResponse(
                    orderService.createOrder(orderMapper.mapOrderRequestToEntity(request)));
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id){
        return orderMapper.mapEntityToOrderResponse(
                    orderService.getOrderById(id));
    }

    @GetMapping("/filter")
    public OrderPagebleResponse getOrdersByFilter( @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", required = false) String sortDir){

        
        Long userId = userService.getCurrentUserId().get();
        Page<Order> orders = orderService.getUserOrders(userId, 
                                                Objects.requireNonNullElse(page, orderConfig.getDefaultPageNumber()),
                                                Objects.requireNonNullElse(pageSize, orderConfig.getDefaultPageSize()),
                                                Objects.requireNonNullElse(sortBy, orderConfig.getDefaultSortBy()),
                                                Objects.requireNonNullElse(sortDir, orderConfig.getDefaultSortOrder()));

        return new OrderPagebleResponse(orders.getContent().stream()
                                        .map(orderMapper::mapEntityToOrderResponse)
                                        .collect(Collectors.toList()),
                                                orders.getNumber(),
                                                orders.getSize(),
                                                orders.getTotalElements(),
                                                orders.getTotalPages(),                                       
                                                orders.isLast());  
    }

}

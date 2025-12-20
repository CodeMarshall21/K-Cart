package com.project.kcart.controller;

import com.project.kcart.dto.CreateOrderRequest;
import com.project.kcart.entity.Orders;
import com.project.kcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest){
        Orders orders = orderService.createOrder(orderRequest);
        return ResponseEntity.ok().body(orders);
    }
}

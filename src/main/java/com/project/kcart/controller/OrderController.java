package com.project.kcart.controller;

import com.project.kcart.dto.CreateOrderRequest;
import com.project.kcart.dto.OrderCreated;
import com.project.kcart.entity.Orders;
import com.project.kcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest){
        OrderCreated orderCreated = orderService.createOrder(orderRequest);
        return ResponseEntity.ok().body(orderCreated);
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<?> getOrder(@PathVariable String orderNo){
        Orders orders = orderService.getOrder(orderNo);

        return ResponseEntity.ok().body(orders);
    }
}

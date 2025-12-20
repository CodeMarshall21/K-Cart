package com.project.kcart.dto;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequest {

    private List<OrderItemDto> orderItems;

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}


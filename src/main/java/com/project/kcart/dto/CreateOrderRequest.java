package com.project.kcart.dto;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequest {
    private List<OrderItemDto> orderItemDto = new ArrayList<>();

    public List<OrderItemDto> getOrderItemDto() {
        return orderItemDto;
    }

    public void setOrderItemDto(List<OrderItemDto> orderItemDto) {
        this.orderItemDto = orderItemDto;
    }
}

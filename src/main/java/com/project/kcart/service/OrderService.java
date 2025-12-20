package com.project.kcart.service;

import com.project.kcart.dto.CreateOrderRequest;
import com.project.kcart.dto.OrderCreated;
import com.project.kcart.dto.OrderItemDto;
import com.project.kcart.entity.OrderItem;
import com.project.kcart.entity.Orders;
import com.project.kcart.entity.Product;
import com.project.kcart.repository.OrderRepository;
import com.project.kcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    OrderRepository orderRepo;

    public OrderCreated createOrder(CreateOrderRequest orderRequest){

        Orders orders = new Orders();
        orders.setStatus("PENDING");
        double totalItemsAmount = 0;
        double taxAmount = 0;

        for(OrderItemDto item: orderRequest.getOrderItems()){
            OrderItem orderItem = new OrderItem();

            orderItem.setName(item.getName());
            orderItem.setPrice(item.getPrice());
            orderItem.setImage(item.getImage());
            orderItem.setQuantity(item.getQuantity());

            Product product = productRepo.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("PRODUCT DO NOT EXISTS"));
            orderItem.setProduct(product);

            totalItemsAmount += item.getPrice() * item.getQuantity();
            taxAmount += item.getPrice()*0.1;

            orders.getOrderItems().add(orderItem);
        }

        orders.setTotalItemsAmount(totalItemsAmount);

        double totalAmount = (double) Math.round((totalItemsAmount + taxAmount) * 100) / 100;
        orders.setTotalAmount(totalAmount);
        taxAmount = (double) Math.round(taxAmount * 100) / 100;
        orders.setTaxAmount(taxAmount);
        String orderNo = UUID.randomUUID().toString();
        orders.setOrderNo(orderNo);

        orderRepo.save(orders);

        return new OrderCreated(orderNo);
    }
}

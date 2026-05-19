package com.example.ecsite.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private int orderId;
    private int totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private List<CartItemDto> items;
}
package com.example.ecsite.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDto {
    private int orderId;
    private int userId;
    private int totalPrice;
    private String status;
    private List<CartItemDto> items;
}
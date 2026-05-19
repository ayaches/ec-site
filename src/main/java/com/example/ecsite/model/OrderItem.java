package com.example.ecsite.model;

import lombok.Data;

@Data
public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private int price;
}
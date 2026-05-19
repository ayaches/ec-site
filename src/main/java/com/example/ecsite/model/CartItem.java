package com.example.ecsite.model;

import lombok.Data;

@Data
public class CartItem {
    private int id;
    private int cartId;
    private int productId;
    private int quantity;
}
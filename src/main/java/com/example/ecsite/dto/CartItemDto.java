package com.example.ecsite.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private int cartItemId;
    private int productId;
    private String productName;
    private int price;
    private int quantity;

    public int getSubtotal() {
        return price * quantity;
    }
}
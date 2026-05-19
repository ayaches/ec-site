package com.example.ecsite.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Order {
    private int id;
    private int userId;
    private int totalPrice;
    private String status;
    private LocalDateTime createdAt;
}
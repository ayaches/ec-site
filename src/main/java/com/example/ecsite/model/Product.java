package com.example.ecsite.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private int stock;
    private String imageUrl;
    private LocalDateTime createdAt;
}
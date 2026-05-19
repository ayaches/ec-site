package com.example.ecsite.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String role;
    private LocalDateTime createdAt;
}
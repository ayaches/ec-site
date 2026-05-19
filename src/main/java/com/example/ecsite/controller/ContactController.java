package com.example.ecsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @GetMapping
    public String form() {
        return "contact";
    }

    @PostMapping
    public String submit() {
        return "redirect:/contact?sent";
    }
}
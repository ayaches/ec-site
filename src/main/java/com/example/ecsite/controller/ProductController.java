package com.example.ecsite.controller;

import com.example.ecsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String root() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/products/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "products/detail";
    }
}
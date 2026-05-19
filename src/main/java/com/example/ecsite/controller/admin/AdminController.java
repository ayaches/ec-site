package com.example.ecsite.controller.admin;

import com.example.ecsite.model.Product;
import com.example.ecsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;

    @GetMapping
    public String dashboard() {
        return "redirect:/admin/products";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/products/list";
    }

    @GetMapping("/products/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/products/create";
    }

    @PostMapping("/products/create")
    public String create(@ModelAttribute Product product) {
        productService.insert(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "admin/products/edit";
    }

    @PostMapping("/products/{id}/update")
    public String update(@PathVariable int id, @ModelAttribute Product product) {
        product.setId(id);
        productService.update(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/{id}/delete")
    public String delete(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }
}
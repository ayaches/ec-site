package com.example.ecsite.controller;

import com.example.ecsite.dto.CartItemDto;
import com.example.ecsite.model.User;
import com.example.ecsite.service.CartService;
import com.example.ecsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @GetMapping
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        int userId = resolveUser(userDetails).getId();
        List<CartItemDto> items = cartService.getCartItems(userId);
        model.addAttribute("items", items);
        model.addAttribute("totalPrice", cartService.getTotalPrice(items));
        return "cart/index";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal UserDetails userDetails,
                      @RequestParam int productId,
                      @RequestParam(defaultValue = "1") int quantity) {
        cartService.addItem(resolveUser(userDetails).getId(), productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String update(@RequestParam int cartItemId,
                         @RequestParam int quantity) {
        if (quantity <= 0) {
            cartService.removeItem(cartItemId);
        } else {
            cartService.updateQuantity(cartItemId, quantity);
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        cartService.removeItem(id);
        return "redirect:/cart";
    }

    private User resolveUser(UserDetails userDetails) {
        return userService.findByEmail(userDetails.getUsername());
    }
}
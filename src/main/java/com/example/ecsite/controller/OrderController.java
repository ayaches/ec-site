package com.example.ecsite.controller;

import com.example.ecsite.dto.CartItemDto;
import com.example.ecsite.model.Order;
import com.example.ecsite.model.User;
import com.example.ecsite.service.CartService;
import com.example.ecsite.service.OrderService;
import com.example.ecsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/confirm")
    public String confirm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        int userId = resolveUser(userDetails).getId();
        List<CartItemDto> items = cartService.getCartItems(userId);
        model.addAttribute("items", items);
        model.addAttribute("totalPrice", cartService.getTotalPrice(items));
        return "order/confirm";
    }

    @PostMapping("/place")
    public String place(@AuthenticationPrincipal UserDetails userDetails) {
        int userId = resolveUser(userDetails).getId();
        Order order = orderService.placeOrder(userId);
        return "redirect:/order/complete?orderId=" + order.getId();
    }

    @GetMapping("/complete")
    public String complete(@RequestParam int orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        model.addAttribute("items", orderService.findItemsByOrderId(orderId));
        return "order/complete";
    }

    @GetMapping("/history")
    public String history(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        int userId = resolveUser(userDetails).getId();
        model.addAttribute("orders", orderService.findByUserId(userId));
        return "order/history";
    }

    private User resolveUser(UserDetails userDetails) {
        return userService.findByEmail(userDetails.getUsername());
    }
}
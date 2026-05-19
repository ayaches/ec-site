package com.example.ecsite.service;

import com.example.ecsite.dto.CartItemDto;
import com.example.ecsite.mapper.OrderMapper;
import com.example.ecsite.model.Order;
import com.example.ecsite.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final CartService cartService;

    @Transactional
    public Order placeOrder(int userId) {
        List<CartItemDto> cartItems = cartService.getCartItems(userId);
        int totalPrice = cartService.getTotalPrice(cartItems);

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setStatus("PENDING");
        orderMapper.insertOrder(order);

        for (CartItemDto dto : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setProductId(dto.getProductId());
            item.setQuantity(dto.getQuantity());
            item.setPrice(dto.getPrice());
            orderMapper.insertOrderItem(item);
        }

        cartService.clear(userId);
        return order;
    }

    public List<Order> findByUserId(int userId) {
        return orderMapper.findByUserId(userId);
    }

    public Order findById(int id) {
        return orderMapper.findById(id);
    }

    public List<OrderItem> findItemsByOrderId(int orderId) {
        return orderMapper.findItemsByOrderId(orderId);
    }
}
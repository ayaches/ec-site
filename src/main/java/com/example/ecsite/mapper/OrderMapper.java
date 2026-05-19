package com.example.ecsite.mapper;

import com.example.ecsite.model.Order;
import com.example.ecsite.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> findByUserId(int userId);

    Order findById(int id);

    void insertOrder(Order order);

    void insertOrderItem(OrderItem item);

    List<OrderItem> findItemsByOrderId(int orderId);
}
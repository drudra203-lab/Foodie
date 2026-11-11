package com.foodie.dao;


import java.util.List;

import com.foodie.model.Order;
import com.foodie.model.OrderItem;

public interface OrderDao {

    // Save order → returns generated orderId
    int saveOrder(Order order);

    // Save each order item
    void saveOrderItem(OrderItem orderItem);

    // Get all orders by user
    List<Order> getOrdersByUser(int userId);
}
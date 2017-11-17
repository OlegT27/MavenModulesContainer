package com.company.webapp.order;

import com.company.webapp.user.User;

import java.util.List;

public interface OrderHiberService {
    List<Order> getOrdersList(User user);

    boolean createOrder(Order order);

    boolean createOrder(Order order, Long key);
}

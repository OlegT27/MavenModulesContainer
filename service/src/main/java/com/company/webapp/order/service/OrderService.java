package com.company.webapp.order.service;

import com.company.webapp.order.entity.Order;
import com.company.webapp.user.entity.User;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersList(User user);

    boolean createOrder(Order order, BindingResult result);
}

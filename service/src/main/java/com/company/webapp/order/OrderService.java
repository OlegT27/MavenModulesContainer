package com.company.webapp.order;

import com.company.webapp.user.User;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersList(User user);

    boolean createOrder(Order order, BindingResult result);
}

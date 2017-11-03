package com.company.webapp.order.service;

import com.company.webapp.order.dao.OrderDAO;
import com.company.webapp.order.entity.Order;
import com.company.webapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderValidator validator;

    @Override
    public List<Order> getOrdersList(User user) {
        return orderDAO.getUserOrders(user);
    }

    @Override
    public boolean createOrder(Order order, BindingResult result) {
        if (!isValidOrder(order, result))
            return false;
        orderDAO.createData(order);
        return true;
    }

    private boolean isValidOrder(Order order, BindingResult result) {
        validator.validate(order, result);
        if (result.hasErrors())
            return false;
        return true;
    }
}

package com.company.webapp.order;

import com.company.webapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<Order> getOrdersList(User user) {
        return orderDAO.getUserOrders(user);
    }

    @Override
    public boolean createOrder(Order order) {
        if (orderDAO.createData(order) != -1)
            return true;
        return false;
    }

}

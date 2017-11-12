package com.company.webapp.order;

import com.company.webapp.GenericDao;
import com.company.webapp.user.User;

import java.util.List;

public interface OrderHiberDAO extends GenericDao<Order> {

    Order getOrderByKey(Long key);

    List<Order> getUserOrders(User user);

    long deleteUserOrders(User user);

    long getCountByUser(User user);
}
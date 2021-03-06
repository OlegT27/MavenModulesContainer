package com.company.webapp.order;


import com.company.webapp.GenericDao;
import com.company.webapp.user.User;

import java.util.List;

public interface OrderDAO extends GenericDao<Order> {

    Order getOrderByKey(Long key);

    List<Order> getUserOrders(User user);

    void deleteUserOrders(User user);

    long getCountByUser(User user);
}

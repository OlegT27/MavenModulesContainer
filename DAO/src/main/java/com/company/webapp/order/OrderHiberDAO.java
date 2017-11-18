package com.company.webapp.order;

import com.company.webapp.GenericDao;

import java.util.List;

public interface OrderHiberDAO extends GenericDao<Order> {

    Order getOrderByKey(Long key);

    List<Order> getUserOrders(Long userId);

    long deleteUserOrders(Long userId);

    long getCountByUser(Long userId);
}

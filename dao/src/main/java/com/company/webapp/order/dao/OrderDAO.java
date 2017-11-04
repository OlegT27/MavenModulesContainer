package com.company.webapp.order.dao;


import com.company.webapp.daoutil.GenericDao;
import com.company.webapp.order.entity.Order;
import com.company.webapp.user.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public interface OrderDAO extends GenericDao<Order> {

    Order getOrderByKey(Long key);

    List<Order> getUserOrders(User user);

    int deleteUserOrders(User user);

    int getCountByUser(User user);
}

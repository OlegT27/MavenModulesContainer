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
        OrderValidator validator = new OrderValidator();
        validator.validate(order, result);
        if (result.hasErrors())
            return false;
        return true;
    }

   /* //==========Data fetchers=============
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Order> getAllOrders() {
        return orderDAO.selectData(SQLQuery.ORDER_SELECT_ALL.getQuery());
    }

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Order> getOrderById(Order order) {
        return orderDAO.selectData(SQLQuery.ORDER_SELECT_ONE.getQuery(), order.getOrderId());
    }

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Order> getOrderByUser(int userId) {
        return orderDAO.selectData(SQLQuery.ORDER_SELECT_BY_USER.getQuery(), userId);
    }
    //====================================


    //==========Data updaters=============
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean insertOrder(Order order) {
        return orderDAO.updateData(SQLQuery.ORDER_INSERT.getQuery(), order.getName(), order.getCreateDate(), order.getUserId());
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean updateOrder(Order order) {
        return orderDAO.updateData(SQLQuery.ORDER_UPDATE.getQuery(), order.getName(), order.getOrderId());
    }
    //====================================

    //================Data destroyers=====
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteOrder(Order order) {
        return orderDAO.updateData(SQLQuery.ORDER_DELETE.getQuery(), order.getOrderId());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public boolean deleteOrdersByUser(int userId) {
        return orderDAO.updateData(SQLQuery.ORDER_DELETE_BY_USER_ID.getQuery(), userId);
    }
    //====================================
*/
}

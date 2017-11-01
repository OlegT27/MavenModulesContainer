package com.company.webapp.service.datamanager;

import com.company.webapp.daoutil.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderDataManager {

    @Qualifier("orderDao")
    @Autowired
    private GenericDao orderDAO;

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

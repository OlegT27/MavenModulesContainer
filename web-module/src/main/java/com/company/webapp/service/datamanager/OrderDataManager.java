package com.company.webapp.service.datamanager;

import com.company.webapp.dao.GenericDao;
import com.company.webapp.entity.Order;
import com.company.webapp.service.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDataManager {

    @Qualifier("orderDao")
    @Autowired
    private GenericDao orderDAO;

    public List<Order> getAllOrders() {
        return orderDAO.selectData(SQLQuery.ORDER_SELECT_ALL.getQuery());
    }

    public List<Order> getOrderById(Order order) {
        return orderDAO.selectData(SQLQuery.ORDER_SELECT_ONE.getQuery(), order.getOrderId());
    }

    public boolean deleteOrder(Order order) {
        return orderDAO.updateData(SQLQuery.ORDER_DELETE.getQuery(), order.getOrderId());
    }

    public boolean insertOrder(Order order) {
        return orderDAO.updateData(SQLQuery.ORDER_INSERT.getQuery(), order.getName());
    }

    public boolean updateOrder(Order order) {
        return orderDAO.updateData(SQLQuery.ORDER_UPDATE.getQuery(), order.getName(), order.getOrderId());
    }

}
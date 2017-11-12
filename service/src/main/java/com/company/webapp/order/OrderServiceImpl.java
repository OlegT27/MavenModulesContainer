package com.company.webapp.order;

import com.company.webapp.order.hiber.Order;
import com.company.webapp.user.hiber.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderHiberService {

    // for hibernate and jdbcTemplate
    @Qualifier("orderHibernateDAOImpl")
    @Autowired
    private OrderHiberDAO orderDAO;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<Order> getOrdersList(User user) {
        return orderDAO.getUserOrders(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean createOrder(Order order) {
        return orderDAO.createData(order) != -1;
    }

}

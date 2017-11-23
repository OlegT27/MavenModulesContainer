package com.company.webapp.order;

import com.company.webapp.user.User;
import com.company.webapp.user.UserHiberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderHiberService {

    // for hibernate and jdbcTemplate
    @Qualifier("orderHibernateDAOImpl")
    @Autowired
    private OrderHiberDAO orderDAO;
    @Autowired
    private UserHiberDAO userDAO;

    @Override
    @Transactional
    public List<Order> getOrdersList(User user) {
        return orderDAO.getUserOrders(user.getId());
    }

    @Override
    @Transactional
    public void createOrder(Order order) {
        orderDAO.createData(order);
    }

    @Override
    @Transactional
    public void createOrder(Order order, Long key) {
        order.setUser(userDAO.getUserById(key));
        orderDAO.createData(order);
    }

}

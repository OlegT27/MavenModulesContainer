package com.company.webapp.order;

import com.company.webapp.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderHibernateDAOImpl implements OrderHiberDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order getOrderByKey(Long key) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Order.class, key);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        User currUser = session.get(User.class, userId);
        List<Order> orderList = new ArrayList<>();
        orderList.addAll(currUser.getOrders());
        return orderList;
    }

    @Override
    public void deleteUserOrders(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        User currUser = session.get(User.class, userId);
        currUser.setOrders(null);
    }

    @Override
    public long getCountByUser(Long userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User user = session.get(User.class, userId);
            return user.getOrders().size();
        } catch (NullPointerException ex) {
            // when tryin' to get size form not existing user list;
            return 0;
        }

    }

    @Override
    public List<Order> selectData() {
        Session session = sessionFactory.getCurrentSession();
        List<Order> myList = session.createQuery("from Order").list();
        return myList;

    }

    @Override
    public void updateData(Order updatedOrder) {
        Session session = sessionFactory.getCurrentSession();
        // Get persistent object (PO)
        Order order = session.get(Order.class, updatedOrder.getId());
        // Replace PO's fields with new ones
        order.setName(updatedOrder.getName());
        order.setAddDate(updatedOrder.getAddDate());

    }

    @Override
    public void deleteData(Order record) {
        Session session = sessionFactory.openSession();
        session.delete(record);

    }

    @Override
    public void createData(Order order) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, order.getUser().getId());
        user.getOrders().add(order);
        session.save(order);
    }

    @Override
    public long getCount() {
        Session session = sessionFactory.getCurrentSession();
        Long result = (Long) session.createQuery("select count(id) from Order ").uniqueResult();
        return result;
    }
}

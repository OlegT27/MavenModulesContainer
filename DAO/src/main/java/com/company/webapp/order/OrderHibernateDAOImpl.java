package com.company.webapp.order;

import com.company.webapp.user.User;
import org.hibernate.HibernateException;
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
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Order.class, key);
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return null;
        }
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User currUser = session.get(User.class, userId);
            List<Order> orderList = new ArrayList<>();
            orderList.addAll(currUser.getOrders());
            return orderList;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return null;
        }
    }

    @Override
    public long deleteUserOrders(Long userId) {
        // Using this stuff to get rid of orders which belonged to "not exist" user
        try {
            Session session = sessionFactory.getCurrentSession();
            User currUser = session.get(User.class, userId);
            currUser.setOrders(null);
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        }
    }

    @Override
    public long getCountByUser(Long userId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User user = session.get(User.class, userId);
            return user.getOrders().size();
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        } catch (NullPointerException ex) {
            // when tryin' to get size form not existing user list;
            return 0;
        }

    }

    @Override
    public List<Order> selectData() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Order> myList = session.createQuery("from Order").list();
            return myList;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return null;
        }
    }

    @Override
    public long updateData(Order updatedOrder) {
        try {
            Session session = sessionFactory.getCurrentSession();
            // Get persistent object (PO)
            Order order = session.get(Order.class, updatedOrder.getId());
            // Replace PO's fields with new ones
            order.setName(updatedOrder.getName());
            order.setAddDate(updatedOrder.getAddDate());
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        }
    }

    @Override
    public long deleteData(Order record) {
        try {
            Session session = sessionFactory.openSession();
            session.delete(record);
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        }
    }

    @Override
    public long createData(Order order) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User user = session.get(User.class, order.getUser().getId());
            user.getOrders().add(order);
            session.save(order);
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        }
    }

    @Override
    public long getCount() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Long result = (Long) session.createQuery("select count(id) from Order ").uniqueResult();
            return result;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        }

    }
}

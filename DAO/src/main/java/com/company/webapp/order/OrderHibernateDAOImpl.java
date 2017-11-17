package com.company.webapp.order;

import com.company.webapp.user.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<Order> getUserOrders(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User currUser = session.get(User.class, user.getId());
            Query query = session.createQuery("from Order where user=:currUser");
            query.setParameter("currUser", currUser);
            List<Order> result = query.list();
            return result;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return null;
        }
    }

    @Override
    public long deleteUserOrders(User user) {
        // Using this stuff to get rid of orders which belonged to "not exist" user
        List<Order> orders = getUserOrders(user);
        if (orders == null)
            return 0;
        try {
            Session session = sessionFactory.getCurrentSession();
            for (Order userOrder : orders) {
                session.delete(userOrder);
            }
            return orders.size();
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        }
    }

    @Override
    public long getCountByUser(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("select count(id) from Order where user=:currUser");
            query.setParameter("currUser", user);
            Long result = (Long) query.uniqueResult();
            return result;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
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
    public long createData(Order record) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Long result = (Long) session.save(record);
            return result;
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

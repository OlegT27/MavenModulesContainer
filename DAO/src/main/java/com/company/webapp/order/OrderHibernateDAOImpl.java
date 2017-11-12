package com.company.webapp.order;

import com.company.webapp.order.hiber.Order;
import com.company.webapp.user.hiber.User;
import com.company.webapp.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderHibernateDAOImpl implements OrderHiberDAO {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Order getOrderByKey(Long key) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session = HibernateUtil.getSessionFactory().openSession();
            Order order = session.get(Order.class, key);
            session.getTransaction().commit();
            return order;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return null;
        }
    }

    @Override
    public List<Order> getUserOrders(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Order where user=:currUser");
            query.setParameter("currUser", user);
            List<Order> result = query.list();
            session.getTransaction().commit();
            return result;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return null;
        }
    }

    @Override
    public long deleteUserOrders(User user) {
        List<Order> orders = getUserOrders(user);
        if (orders == null)
            return -1;
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            for (Order userOrder : orders) {
                session.delete(userOrder);
            }
            session.getTransaction().commit();
            return orders.size();
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return -1;
        }
    }

    @Override
    public long getCountByUser(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("select count(id) from Order where user=:currUser");
            query.setParameter("currUser", user);
            Long result = (Long) query.uniqueResult();
            session.getTransaction().commit();
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return -1;
        }

    }

    @Override
    public List<Order> selectData() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<Order> myList = session.createQuery("from Order").list();
            session.getTransaction().commit();
            return myList;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return null;
        }
    }

    @Override
    public long updateData(Order record) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(record);
            session.getTransaction().commit();
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return -1;
        }
    }

    @Override
    public long deleteData(Order record) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(record);
            session.getTransaction().commit();
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return -1;
        }
    }

    @Override
    public long createData(Order record) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Long result = (Long) session.save(record);
            session.getTransaction().commit();
            return result;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return -1;
        }
    }

    @Override
    public long getCount() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Long result = (Long) session.createQuery("select count(id) from Order ").uniqueResult();
            session.getTransaction().commit();
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            try {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            } catch (TransactionException transEx) {
                logger.warn("Can't rollback ->", transEx);
            }
            return -1;
        }

    }
}

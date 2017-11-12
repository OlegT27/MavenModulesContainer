package com.company.webapp.user;

import com.company.webapp.order.OrderHibernateDAOImpl;
import com.company.webapp.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserHibernateDAOImpl implements UserHiberDAO {

    @Autowired
    OrderHibernateDAOImpl orderDAO;

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<User> getAllExistUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> myList = session.createQuery("from User where exist=true").list();
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
    public User getUserById(Long key) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User order = session.get(User.class, key);
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
    public boolean markUserNotExist(User user) {
        orderDAO.deleteUserOrders(user);
        user.setExist(false);
        return updateData(user) == 1;
    }


    @Override
    public List<User> selectData() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> myList = session.createQuery("from User").list();
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
    public long updateData(User record) {
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
    public long deleteData(User record) {
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
    public long createData(User record) {
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
            Long result = (Long) session.createQuery("select count(id) from User").uniqueResult();
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

package com.company.webapp.user;

import com.company.webapp.order.OrderHibernateDAOImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserHibernateDAOImpl implements UserHiberDAO {

    @Autowired
    OrderHibernateDAOImpl orderDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllExistUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from User where exist=true").list();
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return null;
        }
    }

    @Override
    public User getUserById(Long key) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(User.class, key);
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return null;
        }
    }

    @Override
    public boolean markUserNotExist(User userToDel) {

        //orderDAO.deleteUserOrders(userToDel.getId());
        try {
            Session session = sessionFactory.getCurrentSession();
            // Getting persist object (PO)
            User user = session.get(User.class, userToDel.getId());
            // Set field value to false (kind of "update")
            user.setExist(false);
            user.getOrders().clear();
            return true;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return false;
        }
    }


    @Override
    public List<User> selectData() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<User> myList = session.createQuery("from User").list();
            return myList;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return null;
        }
    }

    @Override
    public long updateData(User updatedUser) {
        try {
            Session session = sessionFactory.getCurrentSession();
            // Get persistent object (PO)
            User user = session.get(User.class, updatedUser.getId());
            // Replace PO's fields with new ones
            user.setName(updatedUser.getName());
            user.setSname(updatedUser.getSname());
            user.setPatr(updatedUser.getPatr());
            user.setBdate(updatedUser.getBdate());
            return 1;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        }
    }

    @Override
    public long deleteData(User record) {
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
    public long createData(User record) {
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
            Long result = (Long) session.createQuery("select count(id) from User").uniqueResult();
            return result;
        } catch (HibernateException hiberEx) {
            logger.error("HiberExc ", hiberEx);
            return -1;
        }

    }

}

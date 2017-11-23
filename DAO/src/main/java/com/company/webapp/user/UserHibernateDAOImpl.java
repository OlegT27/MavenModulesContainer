package com.company.webapp.user;

import com.company.webapp.order.OrderHibernateDAOImpl;
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
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where exist=true").list();
    }

    @Override
    public User getUserById(Long key) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, key);

    }

    @Override
    public void markUserNotExist(User userToDel) {

        //orderDAO.deleteUserOrders(userToDel.getId());
        Session session = sessionFactory.getCurrentSession();
        // Getting persist object (PO)
        User user = session.get(User.class, userToDel.getId());
        // Set field value to false (kind of "update")
        user.setExist(false);
        user.getOrders().clear();

    }


    @Override
    public List<User> selectData() {
        Session session = sessionFactory.getCurrentSession();
        List<User> myList = session.createQuery("from User").list();
        return myList;
    }

    @Override
    public void updateData(User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        // Get persistent object (PO)
        User user = session.get(User.class, updatedUser.getId());
        // Replace PO's fields with new ones
        user.setName(updatedUser.getName());
        user.setSname(updatedUser.getSname());
        user.setPatr(updatedUser.getPatr());
        user.setBdate(updatedUser.getBdate());
    }

    @Override
    public void deleteData(User record) {
        Session session = sessionFactory.openSession();
        session.delete(record);
    }

    @Override
    public void createData(User record) {
        Session session = sessionFactory.getCurrentSession();
        session.save(record);
    }

    @Override
    public long getCount() {
        Session session = sessionFactory.getCurrentSession();
        Long result = (Long) session.createQuery("select count(id) from User").uniqueResult();
        return result;
    }

}

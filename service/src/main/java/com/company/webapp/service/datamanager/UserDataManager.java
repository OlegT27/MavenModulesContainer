package com.company.webapp.service.datamanager;


import com.company.webapp.daoutil.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserDataManager {

    @Qualifier("userDao")

    @Autowired
    private GenericDao userDAO;

    @Autowired
    OrderDataManager orderManager;
/*
    //==========Data fetchers=============
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.selectData(SQLQuery.USER_SELECT_ALL.getQuery());
    }

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<User> getAllExistUsers() {
        return userDAO.selectData(SQLQuery.USER_SELECT_IF_EXISTS.getQuery());
    }

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public User getUserById(int key) {
        List<User> userList = userDAO.selectData(SQLQuery.USER_SELECT_ONE.getQuery(), key);
        return userList.get(0);
    }

    //====================================
    //==========Data updaters=============
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean updateUser(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        String patron = user.getPatron();
        Date date = user.getBirthDate();
        Integer id = user.getId();
        return userDAO.updateData(SQLQuery.USER_UPDATE.getQuery(), name, surname, patron, date, id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean insertUser(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        String patron = user.getPatron();
        Date date = user.getBirthDate();
        return userDAO.updateData(SQLQuery.USER_INSERT.getQuery(), name, surname, patron, date);
    }

    //====================================
    //================Data destroyers
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteUser(User user) {
        orderManager.deleteOrdersByUser(user.getId());
        return userDAO.updateData(SQLQuery.USER_DELETE.getQuery(), user.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean markUserNotExist(User user) {
        orderManager.deleteOrdersByUser(user.getId());
        return userDAO.updateData(SQLQuery.USER_SET_NOT_EXIST.getQuery(), user.getId());
    }
    //====================================
    */
}

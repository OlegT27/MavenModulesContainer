package com.company.webapp.service.datamanager;


import com.company.webapp.dao.GenericDao;
import com.company.webapp.entity.User;
import com.company.webapp.service.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserDataManager {

    @Qualifier("userDao")
    @Autowired
    private GenericDao userDAO;


    public List<User> getAllUsers() {
        return userDAO.selectData(SQLQuery.USER_SELECT_ALL.getQuery());
    }

    public List<User> getAllExistUsers() {
        return userDAO.selectData(SQLQuery.USER_SELECT_IF_EXISTS.getQuery());
    }

    public List<User> getUserById(int key) {
        return userDAO.selectData(SQLQuery.USER_SELECT_ONE.getQuery(), key);
    }

    public boolean updateUser(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        String patron = user.getPatron();
        Date date = user.getBirthDate();
        Integer id = user.getId();
        return userDAO.updateData(SQLQuery.USER_UPDATE.getQuery(), name, surname, patron, date, id);
    }

    public boolean insertUser(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        String patron = user.getPatron();
        Date date = user.getBirthDate();
        return userDAO.updateData(SQLQuery.USER_INSERT.getQuery(), name, surname, patron, date);
    }

    public boolean deleteUser(User user) {
        return userDAO.updateData(SQLQuery.USER_DELETE.getQuery(), user.getId());
    }

    public boolean markUserNotExist(User user) {
        return userDAO.updateData(SQLQuery.USER_SET_NOT_EXIST.getQuery(), user.getId());
    }

}

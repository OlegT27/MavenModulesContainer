package com.company.webapp.service;


import com.company.webapp.dao.DAO;
import com.company.webapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private DAO userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public boolean delUser(User user) {
        return userDao.delUser(null);
    }

    public boolean addUser(User user) {
        return userDao.addUser(null);
    }

    public List<User> getAllUsers_alpha() {
        return userDao.fetchData(SQLQuery.USER_SELECT_ALL.getQuery(), null);
    }


}

package com.company.webapp.service;


import com.company.webapp.dao.GenericDao;
import com.company.webapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private GenericDao dao;


    public List<User> getAllUsers() {
        return dao.selectData(SQLQuery.USER_SELECT_ALL.getQuery());
    }

    public List<User> getAllExistUsers() {
        return dao.selectData(SQLQuery.USER_SELECT_IF_EXISTS.getQuery());
    }

}

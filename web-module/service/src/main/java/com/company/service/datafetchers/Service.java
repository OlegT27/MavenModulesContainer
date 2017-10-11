package com.company.service.datafetchers;

import com.company.dao.interfaces.GenericDAO;
import com.company.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private GenericDAO dao;

    public List<User> getUsers() {
        return dao.getAllData("SELECT * FROM USERS;");
    }

}

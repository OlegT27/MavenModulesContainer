package com.company.webapp.dao;

import com.company.webapp.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Repository
// TODO : Should be implementation of interface, currently it's just a mock
public class DAO {
    private List<User> users = Arrays.asList(
            new User("Vasya", "Pupkin", " ", Date.valueOf("1994-03-03"), true, 0),
            new User("Petya", "Petkin", " ", Date.valueOf("1994-03-03"), false, 0));

    public List<User> getAllUsers() {
        return users;
    }

    public boolean addUser(User user) {
        return true;
    }

    public boolean delUser(User user) {
        return true;
    }
}

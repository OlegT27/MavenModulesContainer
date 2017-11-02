package com.company.webapp.user.dao;


import com.company.webapp.daoutil.GenericDao;
import com.company.webapp.user.entity.User;

import java.util.List;

public interface UserDAO extends GenericDao<User> {

    List<User> getAllExistUsers();

    User getUserById(int key);

    boolean markUserNotExist(User user);
}

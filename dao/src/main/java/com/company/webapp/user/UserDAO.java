package com.company.webapp.user;


import com.company.webapp.daoutil.GenericDao;

import java.util.List;

public interface UserDAO extends GenericDao<User> {

    List<User> getAllExistUsers();

    User getUserById(Long key);

    boolean markUserNotExist(User user);
}

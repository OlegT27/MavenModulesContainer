package com.company.webapp.user;

import com.company.webapp.GenericDao;
import com.company.webapp.user.hiber.User;

import java.util.List;

public interface UserHiberDAO extends GenericDao<User> {

    List<User> getAllExistUsers();

    User getUserById(Long key);

    boolean markUserNotExist(User user);
}

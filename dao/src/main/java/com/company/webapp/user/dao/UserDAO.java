package com.company.webapp.user.dao;


import com.company.webapp.daoutil.GenericDao;
import com.company.webapp.user.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
public interface UserDAO extends GenericDao<User> {

    List<User> getAllExistUsers();

    User getUserById(int key);

    boolean markUserNotExist(User user);
}

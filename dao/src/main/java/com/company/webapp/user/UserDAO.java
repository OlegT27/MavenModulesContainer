package com.company.webapp.user;


import com.company.webapp.daoutil.GenericDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public interface UserDAO extends GenericDao<User> {

    List<User> getAllExistUsers();

    User getUserById(Long key);

    boolean markUserNotExist(User user);
}

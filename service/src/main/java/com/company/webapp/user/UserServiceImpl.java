package com.company.webapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserHiberService {

    @Qualifier("userHibernateDAOImpl")
    @Autowired
    private UserHiberDAO userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(User user) {
        user.setExist(true);
        userDao.createData(user);
    }

    @Override
    @Transactional
    public List<User> requestUsersList() {
        return userDao.getAllExistUsers();
    }

    @Override
    @Transactional
    public User getUserToEdit(User user) {
        return userDao.getUserById(user.getId());
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.markUserNotExist(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateData(user);
    }

}

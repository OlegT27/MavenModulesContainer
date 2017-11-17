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
    public boolean createUser(User user) {
        user.setExist(true);
        return userDao.createData(user) == 1;
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
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(User user) {
        userDao.markUserNotExist(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(User user) {
        return userDao.updateData(user) != -1;
    }

}

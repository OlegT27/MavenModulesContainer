package com.company.webapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public boolean createUser(User user) {
        if (userDao.createData(user) != 1)
            return false;
        return true;
    }

    @Override
    public List<User> requestUsersList() {
        return userDao.getAllExistUsers();
    }

    @Override
    public User getUserToEdit(User user) {
        return userDao.getUserById(user.getId());
    }

    @Override
    public void deleteUser(User user) {
        userDao.markUserNotExist(user);
    }

    @Override
    public boolean updateUser(User user) {
        if (userDao.updateData(user) != -1)
            return true;
        return false;
    }

}

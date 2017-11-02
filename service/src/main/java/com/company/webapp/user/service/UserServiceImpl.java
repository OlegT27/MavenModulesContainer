package com.company.webapp.user.service;


import com.company.webapp.user.dao.UserDAO;
import com.company.webapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public boolean submitUser(User user, BindingResult result) {
        if (!isValidUser(user, result))
            return false;
        userDao.createData(user);
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
    public void deleteUser(User userToDel) {
        userDao.markUserNotExist(userToDel);
    }

    @Override
    public boolean updateUser(User user, BindingResult result) {
        if (!isValidUser(user, result))
            return false;
        userDao.updateData(user);
        return true;
    }

    private boolean isValidUser(User user, BindingResult result) {
        UserValidator validator = new UserValidator();
        validator.validate(user, result);
        if (result.hasErrors())
            return false;
        return true;
    }

}

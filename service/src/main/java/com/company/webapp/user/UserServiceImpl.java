package com.company.webapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserValidator validator;

    @Override
    public boolean submitUser(User user, BindingResult result) {
        if (!isValidUser(user, result))
            return false;
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
    public boolean updateUser(User user, BindingResult result) {
        if (!isValidUser(user, result))
            return false;
        userDao.updateData(user);
        return true;
    }

    private boolean isValidUser(User user, BindingResult result) {
        validator.validate(user, result);
        if (result.hasErrors())
            return false;
        return true;
    }

}

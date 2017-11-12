package com.company.webapp.user;

import com.company.webapp.user.hiber.User;

import java.util.List;

public interface UserHiberService {


    boolean createUser(User user);

    List<User> requestUsersList();

    User getUserToEdit(User user);

    void deleteUser(User user);

    boolean updateUser(User user);
}

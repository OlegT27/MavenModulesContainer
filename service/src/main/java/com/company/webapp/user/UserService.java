package com.company.webapp.user;

import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {


    boolean submitUser(User user, BindingResult result);

    List<User> requestUsersList();

    User getUserToEdit(User user);

    void deleteUser(User user);

    boolean updateUser(User user, BindingResult result);
}

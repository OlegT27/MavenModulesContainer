package com.company.webapp.user;

import java.util.List;

public interface UserHiberService {


    void createUser(User user);

    List<User> requestUsersList();

    User getUserToEdit(User user);

    void deleteUser(User user);

    void updateUser(User user);
}

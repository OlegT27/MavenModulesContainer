package com.company.webapp.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("USER_ID"));
        user.setName(resultSet.getString("USER_NAME"));
        user.setSurname(resultSet.getString("USER_SNAME"));
        user.setPatron(resultSet.getString("USER_PATR"));
        user.setBirthDate(resultSet.getDate("USER_BDATE"));
        user.setExist(resultSet.getBoolean("USER_EXIST"));
        return user;
    }
}
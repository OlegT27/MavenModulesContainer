package com.company.webapp.user.dao;

import com.company.webapp.user.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setName(resultSet.getString("NAME"));
        user.setSurname(resultSet.getString("SNAME"));
        user.setPatron(resultSet.getString("PATR"));
        user.setBirthDate(resultSet.getDate("BDATE"));
        user.setExist(resultSet.getBoolean("EXIST"));
        return user;
    }
}
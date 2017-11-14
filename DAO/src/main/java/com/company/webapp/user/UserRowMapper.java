package com.company.webapp.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setName(resultSet.getString("NAME"));
        user.setSname(resultSet.getString("SNAME"));
        user.setPatr(resultSet.getString("PATR"));
        user.setBdate(resultSet.getDate("BDATE"));
        user.setExist(resultSet.getBoolean("EXIST"));
        return user;
    }
}
package com.company.webapp.order;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getLong("ID"));
        order.setName(resultSet.getString("NAME"));
        order.setCreateDate(resultSet.getDate("ADDDATE"));
        order.setUserId(resultSet.getLong("USER_ID"));
        return order;
    }
}
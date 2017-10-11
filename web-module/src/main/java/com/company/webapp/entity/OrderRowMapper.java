package com.company.webapp.entity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Nullable
    @Override
    public Order mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("ORDER_ID"));
        order.setName(resultSet.getString("ORDER_NAME"));
        order.setCreateDate(resultSet.getDate("ORDER_ADDDATE"));
        order.setUserId(resultSet.getInt("ORDER_NAME"));
        return order;
    }
}

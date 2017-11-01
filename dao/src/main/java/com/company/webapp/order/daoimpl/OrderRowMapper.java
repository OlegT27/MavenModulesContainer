package com.company.webapp.order.daoimpl;


import com.company.webapp.order.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("ORDER_ID"));
        order.setName(resultSet.getString("ORDER_NAME"));
        order.setCreateDate(resultSet.getDate("ORDER_ADDDATE"));
        order.setUserId(resultSet.getInt("USER_ID"));
        return order;
    }
}

package com.company.webapp.order.dao;


import com.company.webapp.daoutil.SQLQuery;
import com.company.webapp.order.entity.Order;
import com.company.webapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Order getOrderByKey(int key) {
        return jdbcTemplate.queryForObject(SQLQuery.ORDER_SELECT_ONE.getQuery(), new OrderRowMapper(), key);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return jdbcTemplate.query(SQLQuery.ORDER_SELECT_BY_USER.getQuery(), new OrderRowMapper(), user.getId());
    }

    @Override
    public int deleteUserOrders(User user) {
        return jdbcTemplate.update(SQLQuery.ORDER_DELETE_BY_USER_ID.getQuery(), user.getId());
    }

    @Override
    public int getCountByUser(User user) {
        return jdbcTemplate.queryForObject
                (SQLQuery.ORDER_GET_COUNT_BY_USER.getQuery(), new Object[]{user.getId()}, Integer.class);
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject
                (SQLQuery.ORDER_GET_COUNT.getQuery(), Integer.class);
    }

    @Override
    public List<Order> selectData() {
        return jdbcTemplate.query(SQLQuery.ORDER_SELECT_ALL.getQuery(), new OrderRowMapper());
    }

    @Override
    public int updateData(Order record) {
        return jdbcTemplate.update
                (SQLQuery.ORDER_UPDATE.getQuery(), new OrderRowMapper(), record.getName(), record.getOrderId());
    }

    @Override
    public int deleteData(Order record) {
        return jdbcTemplate.update(SQLQuery.ORDER_DELETE.getQuery(), record.getOrderId());
    }

    @Override
    public int createData(Order record) {
        return jdbcTemplate.update(SQLQuery.ORDER_INSERT.getQuery(), record.getName(), record.getOrderId());
    }
}

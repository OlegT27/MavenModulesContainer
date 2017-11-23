package com.company.webapp.order;


import com.company.webapp.user.User;
import com.company.webapp.util.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
@Deprecated
public class OrderJDBCTemplateDAOImpl implements OrderDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Order getOrderByKey(Long key) {
        return jdbcTemplate.queryForObject(SQLQuery.ORDER_SELECT_ONE.getQuery(), new OrderRowMapper(), key);

    }

    @Override
    public List<Order> getUserOrders(User user) {
        return jdbcTemplate.query(SQLQuery.ORDER_SELECT_BY_USER.getQuery(), new OrderRowMapper(), user.getId());
    }

    @Override
    public void deleteUserOrders(User user) {
        jdbcTemplate.update(SQLQuery.ORDER_DELETE_BY_USER_ID.getQuery(), user.getId());
    }

    @Override
    public long getCountByUser(User user) {
        return jdbcTemplate.queryForObject
                (SQLQuery.ORDER_GET_COUNT_BY_USER.getQuery(), new Object[]{user.getId()}, Integer.class);
    }

    @Override
    public long getCount() {
        return jdbcTemplate.queryForObject
                (SQLQuery.ORDER_GET_COUNT.getQuery(), Integer.class);
    }

    @Override
    public List<Order> selectData() {
        return jdbcTemplate.query(SQLQuery.ORDER_SELECT_ALL.getQuery(), new OrderRowMapper());
    }

    @Override
    public void updateData(Order record) {
        jdbcTemplate.update
                (SQLQuery.ORDER_UPDATE.getQuery(), record.getName(), record.getId());

    }

    @Override
    public void deleteData(Order record) {
        jdbcTemplate.update(SQLQuery.ORDER_DELETE.getQuery(), record.getId());
    }

    @Override
    public void createData(Order record) {
        jdbcTemplate.update(SQLQuery.ORDER_INSERT.getQuery(), record.getName(), record.getId());

    }
}
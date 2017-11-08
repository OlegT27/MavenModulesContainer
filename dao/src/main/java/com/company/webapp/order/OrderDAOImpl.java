package com.company.webapp.order;


import com.company.webapp.daoutil.SQLQuery;
import com.company.webapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Order getOrderByKey(Long key) {
        try {
            return jdbcTemplate.queryForObject(SQLQuery.ORDER_SELECT_ONE.getQuery(), new OrderRowMapper(), key);
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return null;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        try {
            return jdbcTemplate.query(SQLQuery.ORDER_SELECT_BY_USER.getQuery(), new OrderRowMapper(), user.getId());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return null;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public int deleteUserOrders(User user) {
        try {
            return jdbcTemplate.update(SQLQuery.ORDER_DELETE_BY_USER_ID.getQuery(), user.getId());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return -1;
    }

    @Override
    public int getCountByUser(User user) {
        try {
            return jdbcTemplate.queryForObject
                    (SQLQuery.ORDER_GET_COUNT_BY_USER.getQuery(), new Object[]{user.getId()}, Integer.class);
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return -1;
    }

    @Override
    public int getCount() {
        try {
            return jdbcTemplate.queryForObject
                    (SQLQuery.ORDER_GET_COUNT.getQuery(), Integer.class);
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return -1;
    }

    @Override
    public List<Order> selectData() {
        try {
            return jdbcTemplate.query(SQLQuery.ORDER_SELECT_ALL.getQuery(), new OrderRowMapper());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return null;

    }

    @Override
    public int updateData(Order record) {
        try {
            return jdbcTemplate.update
                    (SQLQuery.ORDER_UPDATE.getQuery(), record.getName(), record.getOrderId());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return -1;
    }

    @Override
    public int deleteData(Order record) {
        try {
            return jdbcTemplate.update(SQLQuery.ORDER_DELETE.getQuery(), record.getOrderId());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return -1;
    }

    @Override
    public int createData(Order record) {
        try {
            return jdbcTemplate.update(SQLQuery.ORDER_INSERT.getQuery(), record.getName(), record.getOrderId());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
        return -1;
    }
}
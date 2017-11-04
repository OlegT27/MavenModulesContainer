package com.company.webapp.user.dao;

import com.company.webapp.daoutil.SQLQuery;
import com.company.webapp.order.dao.OrderDAO;
import com.company.webapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderDAO orderManager;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> selectData() {

        try {
            return jdbcTemplate.query
                    (SQLQuery.USER_SELECT_ALL.getQuery(), new UserRowMapper());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
            return null;
        }

    }

    @Override
    public int updateData(User record) {
        try {
            return jdbcTemplate.update
                    (SQLQuery.USER_UPDATE.getQuery(), record.getName(), record.getSurname(), record.getPatron(), record.getBirthDate(), record.getId());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
            return -1;
        }

    }

    @Override
    public int deleteData(User record) {
        try {
            orderManager.deleteUserOrders(record);
            return jdbcTemplate.update(SQLQuery.USER_DELETE.getQuery(), record.getId());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
            return -1;
        }

    }

    @Override
    public int createData(User record) {
        try {
            return jdbcTemplate.update
                    (SQLQuery.USER_INSERT.getQuery(), record.getName(), record.getSurname(), record.getPatron(), record.getBirthDate());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
            return -1;
        }
    }

    @Override
    public int getCount() {
        try {
            return jdbcTemplate.queryForObject
                    (SQLQuery.USER_GET_COUNT.getQuery(), Integer.class);
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
            return -1;
        }

    }

    @Override
    public List<User> getAllExistUsers() {
        try {
            return jdbcTemplate.query
                    (SQLQuery.USER_SELECT_IF_EXISTS.getQuery(), new UserRowMapper());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
            return null;
        }
    }

    @Override
    public User getUserById(Long key) {
        try {
            return jdbcTemplate.queryForObject(SQLQuery.USER_SELECT_ONE.getQuery(), new UserRowMapper(), key);
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
            return null;
        }
    }

    @Override
    public boolean markUserNotExist(User user) {
        try {
            orderManager.deleteUserOrders(user);
            jdbcTemplate.update(SQLQuery.USER_SET_NOT_EXIST.getQuery(), user.getId());
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
            return false;
        }
        return true;

    }
}

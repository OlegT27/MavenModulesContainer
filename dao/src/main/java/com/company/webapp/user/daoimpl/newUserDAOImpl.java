package com.company.webapp.user.daoimpl;

import com.company.webapp.daoutil.SQLQuery;
import com.company.webapp.order.dao.newOrderDAO;
import com.company.webapp.user.dao.newUserDAO;
import com.company.webapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class newUserDAOImpl implements newUserDAO {

    private JdbcTemplate jdbcTemplate;
    private newOrderDAO orderManager;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> selectData() {
        return jdbcTemplate.query
                (SQLQuery.USER_SELECT_ALL.getQuery(), new UserRowMapper());
    }

    @Override
    public int updateData(User record) {
        return jdbcTemplate.update
                (SQLQuery.USER_UPDATE.getQuery(), new UserRowMapper(),
                        record.getName(), record.getSurname(), record.getPatron(), record.getBirthDate(), record.getId());
    }

    @Override
    public int deleteData(User record) {
        orderManager.deleteUserOrders(record);
        jdbcTemplate.update(SQLQuery.USER_DELETE.getQuery(), record.getId());
        return 0;
    }

    @Override
    public int createData(User record) {
        return jdbcTemplate.update
                (SQLQuery.USER_INSERT.getQuery(), new UserRowMapper(),
                        record.getName(), record.getSurname(), record.getPatron(), record.getBirthDate());
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject
                (SQLQuery.USER_GET_COUNT.getQuery(), Integer.class);
    }

    @Override
    public List<User> getAllExistUsers() {
        return jdbcTemplate.query
                (SQLQuery.USER_SELECT_IF_EXISTS.getQuery(), new UserRowMapper());
    }

    @Override
    public User getUserById(int key) {
        return jdbcTemplate.queryForObject(SQLQuery.USER_SELECT_ONE.getQuery(), new UserRowMapper(), key);
    }

    @Override
    public boolean markUserNotExist(User user) {
        orderManager.deleteUserOrders(user);
        jdbcTemplate.update(SQLQuery.USER_SET_NOT_EXIST.getQuery(), user.getId());
        return false;
    }
}

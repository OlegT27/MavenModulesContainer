package com.company.webapp.user;

import com.company.webapp.order.OrderDAO;
import com.company.webapp.util.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Deprecated
public class UserJDBCTemplateDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    @Qualifier("orderJDBCTemplateDAOImpl")
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
    public void updateData(User record) {
        jdbcTemplate.update
                (SQLQuery.USER_UPDATE.getQuery(), record.getName(), record.getSname(), record.getPatr(), record.getBdate(), record.getId());

    }

    @Override
    public void deleteData(User record) {
        orderManager.deleteUserOrders(record);
        jdbcTemplate.update(SQLQuery.USER_DELETE.getQuery(), record.getId());
    }

    @Override
    public void createData(User record) {
        jdbcTemplate.update
                (SQLQuery.USER_INSERT.getQuery(), record.getName(), record.getSname(), record.getPatr(), record.getBdate());


    }

    @Override
    public long getCount() {
        return jdbcTemplate.queryForObject
                (SQLQuery.USER_GET_COUNT.getQuery(), Integer.class);
    }

    @Override
    public List<User> getAllExistUsers() {
        return jdbcTemplate.query
                (SQLQuery.USER_SELECT_IF_EXISTS.getQuery(), new UserRowMapper());
    }

    @Override
    public User getUserById(Long key) {
        return jdbcTemplate.queryForObject(SQLQuery.USER_SELECT_ONE.getQuery(), new UserRowMapper(), key);
    }

    @Override
    public void markUserNotExist(User user) {
        orderManager.deleteUserOrders(user);
        jdbcTemplate.update(SQLQuery.USER_SET_NOT_EXIST.getQuery(), user.getId());
    }
}

package com.company.webapp.daoutil;

import com.company.webapp.user.daoimpl.UserRowMapper;
import com.company.webapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class UserDao implements GenericDao<User> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> selectData(String sqlQuery, Object... args) {
        return jdbcTemplate.query(sqlQuery, new UserRowMapper(), args);
    }

    public boolean updateData(String sqlQuery, Object... args) {

        return jdbcTemplate.update(sqlQuery, args) > 0;
    }

    @Override
    public List<User> selectData() {
        return null;
    }

    @Override
    public int updateData(User record) {
        return 0;
    }

    @Override
    public int deleteData(User record) {
        return 0;
    }

    @Override
    public int createData(User record) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }
}

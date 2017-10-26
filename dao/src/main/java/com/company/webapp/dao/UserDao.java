package com.company.webapp.dao;

import com.company.webapp.entity.User;
import com.company.webapp.entity.UserRowMapper;
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

    @Override
    public List<User> selectData(String sqlQuery, Object... args) {
        return jdbcTemplate.query(sqlQuery, new UserRowMapper(), args);
    }

    @Override
    public boolean updateData(String sqlQuery, Object... args) {

        return jdbcTemplate.update(sqlQuery, args) > 0;
    }


}

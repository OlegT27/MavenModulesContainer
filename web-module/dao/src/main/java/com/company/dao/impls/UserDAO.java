package com.company.dao.impls;

import com.company.dao.interfaces.GenericDAO;
import com.company.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAO implements GenericDAO<User> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addData(User record, String query) {
        return jdbcTemplate.update(query, record) == 0 ? false : true;
    }

    @Override
    public boolean deleteData(User record, String query) {
        return jdbcTemplate.update(query, record) == 0 ? false : true;
    }

    @Override
    public List<User> getAllData(String query) {
        return jdbcTemplate.queryForList(query, User.class);
    }

    @Override
    public User getDataById(int key, String query) {
        return null;
    }

    @Override
    public User updateData(User record, String query) {
        return null;
    }
}

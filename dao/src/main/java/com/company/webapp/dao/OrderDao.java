package com.company.webapp.dao;


import com.company.webapp.entity.Order;
import com.company.webapp.entity.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDao implements GenericDao<Order> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Order> selectData(String sqlQuery, Object... args) {
        return jdbcTemplate.query(sqlQuery, new OrderRowMapper(), args);
    }

    @Override
    public boolean updateData(String sqlQuery, Object... args) {
        return jdbcTemplate.update(sqlQuery, args) > 0;
    }
}

package com.company.webapp.daoutil;

import com.company.webapp.order.daoimpl.OrderRowMapper;
import com.company.webapp.order.entity.Order;
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

    public List<Order> selectData(String sqlQuery, Object... args) {
        return jdbcTemplate.query(sqlQuery, new OrderRowMapper(), args);
    }

    public boolean updateData(String sqlQuery, Object... args) {
        return jdbcTemplate.update(sqlQuery, args) > 0;
    }

    @Override
    public List<Order> selectData() {
        return null;
    }

    @Override
    public int updateData(Order record) {
        return 0;
    }

    @Override
    public int deleteData(Order record) {
        return 0;
    }

    @Override
    public int createData(Order record) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }
}

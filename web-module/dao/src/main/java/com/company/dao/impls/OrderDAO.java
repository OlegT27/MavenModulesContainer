package com.company.dao.impls;

import com.company.dao.interfaces.GenericDAO;
import com.company.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDAO implements GenericDAO<Order> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public boolean addData(Order record, String query) {
        return jdbcTemplate.update(query, record) == 0 ? false : true;
    }

    @Override
    public boolean deleteData(Order record, String query) {
        return jdbcTemplate.update(query, record) == 0 ? false : true;
    }

    @Override
    public List<Order> getAllData(String query) {
        return jdbcTemplate.queryForList(query, Order.class);
    }

    @Override
    public Order getDataById(int key, String query) {
        return null;
    }

    @Override
    public Order updateData(Order record, String query) {
        return null;
    }
}

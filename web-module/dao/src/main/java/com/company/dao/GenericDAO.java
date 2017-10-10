package com.company.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.List;

public abstract class GenericDAO<T> {

    private String datasourceContext;

    public GenericDAO() {
    }

    public GenericDAO(String datasourceContext) {
        this.datasourceContext = datasourceContext;
    }

    public JdbcTemplate getJDBCTemplate() throws NamingException {
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(datasourceContext);
        return new JdbcTemplate(dataSource);
    }

    public abstract boolean addData(T record, String querry);

    public abstract boolean deleteData(int key, String querry);

    public abstract List<T> getAllData(String querry);

    public abstract T getDataById(int key, String querry);

    public abstract T updateData(T record, String querry);


}

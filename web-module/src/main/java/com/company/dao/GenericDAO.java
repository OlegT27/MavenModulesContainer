package com.company.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDAO<T> {

    /*private String connectionLink;
    private String login;
    private String password;
    private String driverURL;*/
    private String datasourceContext;

    /* public GenericDAO(String url, String user, String password, String driver) {
         this.connectionLink = url;
         this.login = user;
         this.password = password;
         this.driverURL = driver;
     }*/
    public GenericDAO(String datasourceContext) {
        this.datasourceContext = datasourceContext;
    }


    public Connection getDBConnection() throws SQLException, ClassNotFoundException, NamingException {
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(datasourceContext);
        Connection conn = dataSource.getConnection();
        return conn;
    }

    public abstract boolean addData(T record);

    public abstract boolean deleteData(int key);

    public abstract List<T> getAllData();

    public abstract T getDataById(int key);

    public abstract T updateData(T record);


}

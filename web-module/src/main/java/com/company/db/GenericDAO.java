package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDAO<T> {

    private String connectionLink;
    private String login;
    private String password;
    private String driverURL;

    public GenericDAO(String url, String user, String password, String driver) {
        this.connectionLink = url;
        this.login = user;
        this.password = password;
        this.driverURL = driver;
    }

    public Connection getDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driverURL);
        Connection conn = DriverManager.getConnection(connectionLink, login, password);
        return conn;
    }

    public abstract boolean addData(T record);

    public abstract boolean deleteData(int key);

    public abstract List<T> getAllData();

    public abstract T getDataById(int key);

    public abstract T updateData(T record);


}

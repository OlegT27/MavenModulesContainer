package com.company.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private Logger logger = LoggerFactory.getLogger(DatabaseAccess.class);
    private String connectionURL;
    private String username;
    private String password;

    public DatabaseAccess() {
    }

    public DatabaseAccess(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public DatabaseAccess(String connectionURL, String username, String password) {
        this.connectionURL = connectionURL;
        this.username = username;
        this.password = password;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection connectDB() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        return conn;
    }

    @Deprecated
    public boolean executeSQLFromFile(String fileName) {

        try {
            Statement state = this.connectDB().createStatement();
            String sqlQuerry = new String();
            for (String sqlLine : Files.readAllLines(Paths.get(fileName))) {
                sqlQuerry += sqlLine;
                System.out.println(sqlLine);
                state.executeUpdate(sqlQuerry);
            }
        } catch (SQLException e) {
            logger.error("SQLException",e);
            return false;
        } catch (IOException e) {
            logger.error("IOException",e);
            return false;
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException",e);
            return false;
        }
        return true;

    }
    @Deprecated
    public List<User> getAllData() throws SQLException, ClassNotFoundException {
        List<User> dataList = new ArrayList<User>();
        Connection connection = this.connectDB();
        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("SELECT * FROM USERS");
        while (resultSet.next()) {
            User record = new User();
            record.setId(resultSet.getInt("USER_ID"));
            record.setName(resultSet.getString("USER_NAME"));
            record.setSurname(resultSet.getString("USER_SNAME"));
            record.setPatron(resultSet.getString("USER_PATR"));
            record.setBirthDate(resultSet.getDate("USER_BDATE"));
            record.setExist(resultSet.getBoolean("USER_EXIST"));
            dataList.add(record);
        }
        connection.close();
        return dataList;
    }
    public List<User> getLivingPeople() throws SQLException, ClassNotFoundException {
        List<User> dataList = new ArrayList<User>();
        Connection connection = this.connectDB();
        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("SELECT * FROM USERS WHERE USER_EXIST = TRUE");
        while (resultSet.next()) {
            User record = new User();
            record.setId(resultSet.getInt("USER_ID"));
            record.setName(resultSet.getString("USER_NAME"));
            record.setSurname(resultSet.getString("USER_SNAME"));
            record.setPatron(resultSet.getString("USER_PATR"));
            record.setBirthDate(resultSet.getDate("USER_BDATE"));
            record.setExist(resultSet.getBoolean("USER_EXIST"));
            dataList.add(record);
        }
        connection.close();
        return dataList;
    }

   public boolean deleteUser(Integer id) throws ClassNotFoundException {
        try {
            Connection connection = this.connectDB();
            Statement state = connection.createStatement();
            state.executeUpdate("UPDATE USERS SET USER_EXIST = 'FALSE' " +
                    "WHERE USER_ID =" +id);
            connection.close();
            return true;
        } catch (SQLException e) {
            logger.error("SQLException",e);
            return false;
        }
    }

    public boolean addData(User record) throws ClassNotFoundException {
        try {
            Connection connection = this.connectDB();
            Statement state = connection.createStatement();
            String sqlQuerry = "INSERT INTO USERS (USER_NAME,USER_SNAME,USER_PATR,USER_BDATE) VALUES('";
            sqlQuerry += record.getName() + "','" + record.getSurname() + "','" + record.getPatron() + "','" + record.getBirthDate() + "');";
            System.out.println(sqlQuerry);
            state.executeUpdate(sqlQuerry);
            connection.close();
            return true;
        } catch (SQLException e) {
            logger.error("SQLException",e);
            return false;
        }

    }

}





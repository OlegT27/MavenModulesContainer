package com.company.db;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class DatabaseAccess {

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

    private Connection connectDB() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        return conn;
    }

    public boolean executeSQLFromFile(String fileName) {

        try {
            Statement state = this.connectDB().createStatement();
            String sqlQuerry = new String();
            for (String sqlLine :
                    readAllLines(Paths.get(fileName))) {
                sqlQuerry += sqlLine;
                System.out.println(sqlLine);
            }

            state.executeUpdate(sqlQuerry);

        } catch (SQLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;

    }

    public List<DatabaseObject> getAllData() {
        List<DatabaseObject> dataList = new ArrayList<DatabaseObject>();
        try {
            Connection connection = this.connectDB();
            Statement state = connection.createStatement();
            ResultSet resultSet = state.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                DatabaseObject record = new DatabaseObject();
                record.setId(resultSet.getInt("USER_ID"));
                record.setName(resultSet.getString("USER_NAME"));
                record.setSurname(resultSet.getString("USER_SNAME"));
                record.setPatron(resultSet.getString("USER_PATR"));
                record.setBirthDate(resultSet.getDate("USER_BDATE"));
                record.setExist(resultSet.getBoolean("USER_EXIST"));
                dataList.add(record);
            }
            return dataList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUser(DatabaseObject record) {
        try {
            Connection connection = this.connectDB();
            Statement state = connection.createStatement();
            state.executeUpdate("UPDATE USERS SET USER_EXIST = 'FALSE' " +
                    "WHERE USER_ID =" +record.getId());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addData(DatabaseObject record) {
        try {
            Connection connection = this.connectDB();
            Statement state = connection.createStatement();
            String sqlQuerry = "INSERT INTO USERS (USER_NAME,USER_SNAME,USER_PATR,USER_BDATE) VALUES('";
            sqlQuerry += record.getName() + "','" + record.getSurname() + "','" + record.getPatron() + "','" + record.getBirthDate() + "');";
            System.out.println(sqlQuerry);
            state.executeUpdate(sqlQuerry);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


   /* public static void main(String[] args) {
        String postgresURL = "jdbc:postgresql://127.0.0.1:5432/users_db";
        String login = "postgres";
        String password = "megapass";
        String fileName = "C:\\DROP_TABLE.sql";
        DatabaseAccess dao = new DatabaseAccess(postgresURL, login, password);
        DatabaseObject object = new DatabaseObject();
        dao.executeSQLFromFile(fileName);
        fileName = "C:\\CREATE_TABLE.sql";
        dao.executeSQLFromFile(fileName);
        object.setName("Oleg");
        object.setSurname("Oleg");
        object.setPatron("Oleg");
        object.setBirthDate(Date.valueOf("1994-05-22"));
        dao.addData(object);
        List<DatabaseObject> list = dao.getAllData();
        for (DatabaseObject l : list) {
            System.out.println(l.getId() + l.getBirthDate().toString() + l.getName() + l.getExist());
        }
        list.clear();
        dao.deleteUser(object);
        list = dao.getAllData();
        for (DatabaseObject l : list) {
            System.out.println(l.getId() + l.getBirthDate().toString() + l.getName() + l.getExist());
        }


        //dao.executeSQLFromFile(fileName);
        //fileName = "C:\\CREATE_TABLE.sql";
        // dao.executeSQLFromFile(fileName);

    }*/
}





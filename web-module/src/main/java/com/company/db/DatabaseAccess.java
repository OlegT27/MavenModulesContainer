package com.company.db;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;

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

    public boolean getAllData(String tableName)
    {
        try {
            Connection connection = this.connectDB();
            Statement state = connection.createStatement();
            ResultSet resultSet = state.executeQuery("SELECT * FROM" + tableName);
            while (resultSet.next())
            {
                System.out.println(resultSet.getString("USER_NAME"));
            }

        } catch (SQLException e) {
            return false;
        }
        return true;

    }


    /*public static void main(String[] args) {
        String postgresURL = "jdbc:postgresql://127.0.0.1:5432/users_db";
        String login = "postgres";
        String password = "megapass";
        String fileName = "C:\\DROP_TABLE.sql";
        DatabaseAccess dao = new DatabaseAccess(postgresURL,login,password);
        dao.executeSQLFromFile(fileName);
        fileName = "C:\\CREATE_TABLE.sql";
        dao.executeSQLFromFile(fileName);

    }*/
}





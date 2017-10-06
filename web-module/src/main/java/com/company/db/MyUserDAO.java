package com.company.db;

import com.company.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyUserDAO extends GenericDAO<User> {

    private Logger logger = LoggerFactory.getLogger(MyUserDAO.class);

    public MyUserDAO(String url, String user, String password, String driver) {
        super(url, user, password, driver);
    }

    @Override
    public boolean addData(User record) {
        try (Connection connection = this.getDBConnection()) {
            Statement state = connection.createStatement();
            state.executeUpdate("INSERT INTO USERS " +
                    "(USER_NAME,USER_SNAME,USER_PATR,USER_BDATE) " +
                    "VALUES('" + record.getName() + "','" + record.getSurname() +
                    "','" + record.getPatron() + "','" + record.getBirthDate() + "');");
            return true;
        } catch (SQLException e) {
            logger.error("SQL exception at addData", e);
            return false;
        } catch (ClassNotFoundException e) {
            logger.error("Can't find driver", e);
            return false;
        }
    }

    @Override
    public boolean deleteData(int id) {
        try (Connection connection = this.getDBConnection()) {
            Statement state = connection.createStatement();
            state.executeQuery("DELETE FROM USERS " +
                    "WHERE USER_ID =" + id);
            return true;
        } catch (SQLException e) {
            logger.error("SQL exception at deleteData", e);
            return false;
        } catch (ClassNotFoundException e) {
            logger.error("Can't find driver", e);
            return false;
        }

    }

    @Override
    public List<User> getAllData() {
        return this.getUsersWhoExist();
    }

    @Override
    public User getDataById(int id) {
        try (Connection connection = this.getDBConnection()) {
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM USERS " +
                    "WHERE USER_ID =" + id);
            User record = new User();
            // some keys which equal
            if (rs.getMetaData().getColumnCount() > 1)
                return null;
            record.setId(rs.getInt("USER_ID"));
            record.setName(rs.getString("USER_NAME"));
            record.setSurname(rs.getString("USER_SNAME"));
            record.setPatron(rs.getString("USER_PATR"));
            record.setBirthDate(rs.getDate("USER_BDATE"));
            record.setExist(rs.getBoolean("USER_EXIST"));
            return record;
        } catch (SQLException e) {
            logger.error("SQL exception at getDataById", e);
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("Can't find driver", e);
            return null;
        }
    }

    public boolean executeSQLFromFile(String fileName) {
        try (Statement state = this.getDBConnection().createStatement()) {
            String sqlQuery = new String();
            for (String sqlLine : Files.readAllLines(Paths.get(fileName))) {
                sqlQuery += sqlLine;
                state.executeUpdate(sqlQuery);
            }
        } catch (SQLException e) {
            logger.error("SQL exception at executeSQLFromFile", e);
            return false;
        } catch (IOException e) {
            logger.error("IO exception at executeSQLFromFile", e);
            return false;
        } catch (ClassNotFoundException e) {
            logger.error("Can't find driver", e);
            return false;
        }
        return true;

    }

    public List<User> getUsersWhoExist() {
        List<User> dataList = new ArrayList<User>();
        try (Connection connection = this.getDBConnection()) {
            Statement state = connection.createStatement();
            ResultSet resultSet = state.executeQuery("SELECT * FROM USERS " +
                    "WHERE USER_EXIST = TRUE");
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
            return dataList;
        } catch (SQLException e) {
            logger.error("SQL exception at getAllData", e);
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("Can't find driver", e);
            return null;
        }
    }

    public boolean setUserAsDeleted(int id) {
        try (Connection connection = this.getDBConnection()) {
            Statement state = connection.createStatement();
            state.executeUpdate("UPDATE USERS SET USER_EXIST = 'FALSE' " +
                    "WHERE USER_ID =" + id);
            return true;
        } catch (SQLException e) {
            logger.error("SQL exception at setUserAsDeleted", e);
            return false;
        } catch (ClassNotFoundException e) {
            logger.error("Can't find driver", e);
            return false;
        }

    }

    @Override
    public User updateData(User user) {
        if (this.setUserAsDeleted(user.getId()))
            return user;
        return null;
    }
}

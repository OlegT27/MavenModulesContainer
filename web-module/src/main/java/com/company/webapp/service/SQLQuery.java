package com.company.webapp.service;

public enum SQLQuery {
    //======USERS SQL=========
    USER_SELECT_ALL("SELECT * FROM USERS;"),
    USER_DELETE("DELETE FROM USERS WHERE USER_ID =?;"),
    USER_INSERT("INSERT INTO USERS(USER_NAME,USER_SNAME,USER_PATR,USER_BDATE) VALUES (?,?,?,?);"),
    USER_UPDATE("UPDATE USERS SET (USER_NAME,USER_SNAME,USER_PATR,USER_BDATE) = (?,?,?,?) WHERE USER_ID = ?;"),
    USER_SELECT_ONE("SELECT * FROM USERS WHERE USER_ID = ?;"),
    USER_SELECT_IF_EXISTS("SELECT * FROM USERS WHERE USER_EXIST = TRUE;"),
    USER_SET_NOT_EXIST("UPDATE USERS SET USER_EXIST = TRUE WHERE USER_ID = ?;"),

    //======ORDERS SQL=========
    ORDER_SELECT_ALL("SELECT * FROM ORDERS;"),
    ORDER_DELETE("DELETE FROM ORDERS WHERE ORDER_ID =?;"),
    ORDER_INSERT("INSERT INTO ORDERS(ORDER_NAME) VALUES (?);"),
    ORDER_UPDATE("UPDATE ORDERS SET (ORDER_NAME) = (?) WHERE ORDER_ID = ?;"),
    ORDER_SELECT_ONE("SELECT * FROM ORDERS WHERE ORDER_ID = ?;");
    //=======================

    private String query;

    SQLQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
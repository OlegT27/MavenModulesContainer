package com.company.model;

import java.sql.Date;

public class Order {

    private Integer id;
    private String name;
    private Date date;
    private Integer userId;

    public Order(Integer id, String name, Date date, Integer userId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}

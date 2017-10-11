package com.company.webapp.entity;

import java.sql.Date;

public class Order {

    private Integer orderId;
    private String name;
    private Date createDate;
    private Integer userId;

    public Order() {
    }

    public Order(Integer orderId, String name, Date createDate, Integer userId) {
        this.orderId = orderId;
        this.name = name;
        this.createDate = createDate;
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

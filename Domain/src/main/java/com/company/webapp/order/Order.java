package com.company.webapp.order;


import java.sql.Date;

public class Order {

    private Long orderId;
    private String name;
    private Date createDate;
    private Long userId;

    public Order() {
    }

    public Order(Long orderId, String name, Date createDate, Long userId) {
        this.orderId = orderId;
        this.name = name;
        this.createDate = createDate;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return orderId + ' ' +
                name + ' ' +
                createDate + ' ' +
                userId
                ;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

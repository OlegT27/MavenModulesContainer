package com.company.webapp.hiber;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders", schema = "public", catalog = "users_db")
public class Order {
    private long id;
    private String name;
    private Date addDate;
    private User user;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "adddate", nullable = true)
    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date adddate) {
        this.addDate = adddate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        if (addDate != null ? !addDate.equals(order.addDate) : order.addDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (addDate != null ? addDate.hashCode() : 0);
        return result;
    }
}

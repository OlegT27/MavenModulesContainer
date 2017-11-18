package com.company.webapp.order;


import com.company.webapp.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "orders", schema = "public", catalog = "users_db")
public class Order implements Serializable {
    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "orders_order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "adddate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant addDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public Instant getAddDate() {
        return addDate;
    }

    public void setAddDate(Instant adddate) {
        this.addDate = adddate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return id + ' ' +
                name + ' ' +
                addDate + ' ' +
                user
                ;
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

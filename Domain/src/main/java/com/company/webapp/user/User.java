package com.company.webapp.user;

import com.company.webapp.order.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "users", schema = "public", catalog = "users_db")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")

    private long id;
    @Column(name = "name")
    @NotBlank
    private String name;
    @Column(name = "sname")
    @NotBlank
    private String sname;
    @Column(name = "patr")
    @NotBlank
    private String patr;
    @Column(name = "bdate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate bdate;
    @Column(name = "exist")
    private Boolean exist;
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Order> orders;

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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname.trim();
    }

    public String getPatr() {
        return patr;
    }

    public void setPatr(String patr) {
        this.patr = patr.trim();
    }

    public LocalDate getBdate() {
        return bdate;
    }

    public void setBdate(LocalDate bdate) {
        this.bdate = bdate;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return sname + ' ' + name + ' ' +
                patr + ' ' + bdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (sname != null ? !sname.equals(user.sname) : user.sname != null) return false;
        if (patr != null ? !patr.equals(user.patr) : user.patr != null) return false;
        if (bdate != null ? !bdate.equals(user.bdate) : user.bdate != null) return false;
        if (exist != null ? !exist.equals(user.exist) : user.exist != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (patr != null ? patr.hashCode() : 0);
        result = 31 * result + (bdate != null ? bdate.hashCode() : 0);
        result = 31 * result + (exist != null ? exist.hashCode() : 0);
        return result;
    }
}

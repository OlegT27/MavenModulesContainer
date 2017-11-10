package com.company.webapp.hiber;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users", schema = "public", catalog = "users_db")
public class User {
    private long id;
    private String name;
    private String sname;
    private String patr;
    private Date bdate;
    private Boolean exist;

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
    @Column(name = "sname", nullable = true, length = 50)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "patr", nullable = true, length = 50)
    public String getPatr() {
        return patr;
    }

    public void setPatr(String patr) {
        this.patr = patr;
    }

    @Basic
    @Column(name = "bdate", nullable = true)
    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    @Basic
    @Column(name = "exist", nullable = true)
    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
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

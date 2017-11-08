package com.company.webapp.user;

import java.sql.Date;

public class User {
    private String name;
    private String surname;
    private String patron;
    private Date birthDate;
    private Boolean exist;
    private Long id;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String name, String surname, String patron, Date birthDate, Boolean exist, Long id) {
        this.name = name;
        this.surname = surname;
        this.patron = patron;
        this.birthDate = birthDate;
        this.exist = exist;
        this.id = id;
    }

    @Override
    public String toString() {
        return surname + ' ' + name + ' ' +
                patron + ' ' + birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

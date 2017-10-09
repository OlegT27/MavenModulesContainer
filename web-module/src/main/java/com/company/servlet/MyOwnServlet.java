package com.company.servlet;

import com.company.dao.GenericDAO;
import com.company.dao.MyUserDAO;
import com.company.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

public class MyOwnServlet extends HttpServlet {
    /*static final String postgresURL = "jdbc:postgresql://127.0.0.1:5432/users_db";
    static final String login = "postgres";
    static final String password = "megapass";
    static final String driver = "org.postgresql.Driver";*/
    static final String dataSource = "java:comp/env/jdbc/postgres";

    private Logger logger = LoggerFactory.getLogger(MyOwnServlet.class);
    GenericDAO accessObject = new MyUserDAO(dataSource);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> allData = accessObject.getAllData();
        // LOCALE getter

        Locale locale = req.getLocale();
        System.out.println(locale.getCountry());
        req.setAttribute("userList", allData);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // INSERT
        if (req.getRequestURI().contains("/add")) {
            try {
                accessObject.addData(inputDataValidation(req));
            } catch (NullPointerException e) {
                req.setAttribute("dataNotValid", true);
                req.getRequestDispatcher("users.jsp").forward(req, resp);
            }
            resp.sendRedirect("/webmodule");
        }
        //DELETE
        if (req.getRequestURI().contains("/delete")) {
            User userToDel = new User();
            userToDel.setId(Integer.valueOf(req.getParameter("userId")));
            accessObject.updateData(userToDel);
            resp.sendRedirect("/webmodule");

        }


    }

    public User inputDataValidation(HttpServletRequest req) {
        User userToAdd = new User();

        userToAdd.setName(req.getParameter("user_name"));
        userToAdd.setSurname(req.getParameter("user_sname"));
        userToAdd.setPatron(req.getParameter("user_patr"));

        // some of the fields are empty
        if ((userToAdd.getName().equals(""))
                || (userToAdd.getSurname().equals(""))
                || (userToAdd.getPatron().equals(""))) {
            return null;
        }
        // error occur while convert string to data
        try {
            userToAdd.setBirthDate(Date.valueOf((req.getParameter("user_date"))));
        } catch (IllegalArgumentException e) {
            return null;
        }
        return userToAdd;

    }
}

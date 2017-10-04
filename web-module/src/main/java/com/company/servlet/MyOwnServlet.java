package com.company.servlet;

import com.company.db.DatabaseAccess;
import com.company.db.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MyOwnServlet extends HttpServlet {
    static final String postgresURL = "jdbc:postgresql://127.0.0.1:5432/users_db";
    static final String login = "postgres";
    static final String password = "megapass";
    private Logger logger = LoggerFactory.getLogger(MyOwnServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseAccess accessObject = new DatabaseAccess(postgresURL, login, password);
        try {

            // =================
            // SELECT *
            //List<User> allData = accessObject.getAllData();
            List<User> allData = accessObject.getLivingPeople();
            req.setAttribute("userList", allData);
            // =================
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error("SQLException",e);

        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException",e);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseAccess accessObject = new DatabaseAccess(postgresURL, login, password);
        try {
            // INSERT
            if (req.getRequestURI().contains("/users/add")) {
                User userToAdd = new User();

                userToAdd.setName(req.getParameter("user_name"));
                userToAdd.setSurname(req.getParameter("user_sname"));
                userToAdd.setPatron(req.getParameter("user_patr"));
                userToAdd.setBirthDate(Date.valueOf((req.getParameter("user_date"))));

                accessObject.addData(userToAdd);
                resp.sendRedirect("/webmodule/users");
            }
            //DELETE
            if (req.getRequestURI().contains("/users/delete")) {
                Integer idToDelete = Integer.valueOf(req.getParameter("idToDelete"));
                accessObject.deleteUser(idToDelete);
                resp.sendRedirect("/webmodule/users");

            }

        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException",e);

        }
    }
}

package com.company.servlet;

import com.company.db.DatabaseAccess;
import com.company.db.DatabaseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyOwnServlet extends HttpServlet {
    static final String postgresURL = "jdbc:postgresql://127.0.0.1:5432/users_db";
    static final String login = "postgres";
    static final String password = "megapass";
    private Logger logger = LoggerFactory.getLogger(MyOwnServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseAccess accessObject = new DatabaseAccess(postgresURL,login,password);
        logger.debug(postgresURL + login + password);
        // on SELECT *
        req.setAttribute("viewObject",accessObject.getAllData());
        req.getRequestDispatcher("users.jsp").forward(req, resp);
        // =================
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // on INSERT
        DatabaseAccess accessObject = new DatabaseAccess(postgresURL,login,password);
        DatabaseObject data = new DatabaseObject();

        data = (DatabaseObject) req.getAttribute("viewObject");
        accessObject.addData(data);

        req.setAttribute("viewObject", data);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
        // ===================

    }
}

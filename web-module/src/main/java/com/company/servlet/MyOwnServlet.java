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
    private Logger logger = LoggerFactory.getLogger(MyOwnServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("textValue","I'am a little parrot!");
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String data = req.getParameter("textValue");
        req.setAttribute("textValue",data);*/
        DatabaseAccess accessObject = new DatabaseAccess();
        req.setAttribute("respObject",new DatabaseObject());
        req.getRequestDispatcher("users.jsp").forward(req,resp);



    }
}

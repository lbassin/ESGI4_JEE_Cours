package com.tp.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserNew extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") == null){
            System.out.println("Access Denied");
            resp.sendRedirect("/login");
            return;
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/user.new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") == null){
            System.out.println("Access Denied");
            resp.sendRedirect("/login");
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Connection connection = Login.getDBConnection();
        String query = "INSERT INTO users(username, password) VALUES (?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/dashboard");
    }
}

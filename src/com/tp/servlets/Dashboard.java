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

public class Dashboard extends HttpServlet{


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Dashboard");
        if(req.getSession().getAttribute("username") == null){
            System.out.println("Access Denied");
            resp.sendRedirect("/login");
            return;
        }

        Connection connection = Login.getDBConnection();
        String query = "SELECT * FROM `users`";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            req.setAttribute("users", rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
    }

}

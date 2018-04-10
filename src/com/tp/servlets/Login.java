package com.tp.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class Login extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean connectionSuccess = false;

        Connection connection = Login.getDBConnection();
        String query = "SELECT * FROM `users` WHERE username = ? AND password = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();

            connectionSuccess = rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connectionSuccess) {
            System.out.println("Connected");
            req.getSession().setAttribute("username", username);
            resp.sendRedirect("/dashboard");
        } else {
            System.out.println("Wrong credentials");
            req.getSession().setAttribute("error", "Wrong credentials");
            resp.sendRedirect("/login");
        }
    }

    public static Connection getDBConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

}

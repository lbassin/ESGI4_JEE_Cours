<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<%@include file="header.jsp" %>

<h1>Dashboard</h1>
<h2>Bonjour <%= request.getSession().getAttribute("username") %></h2>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Username</td>
        <td>Password</td>
    </tr>
    <% if (request.getAttribute("users") != null) {
        try {
            ResultSet users = (ResultSet) request.getAttribute("users");
            while (users.next()) {
                out.print("<tr>");
                out.print("<td>" + users.getString(1) + "</td>");
                out.print("<td>" + users.getString(2) + "</td>");
                out.print("<td>" + users.getString(3) + "</td>");
                out.print("<tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }%>
</table>
<a href="/user/new">Add user</a>
<hr>
<a href="/logout">Logout</a>
</body>
</html>

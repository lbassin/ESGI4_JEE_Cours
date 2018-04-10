<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Login</h1>
<form method="post">
    <% if (request.getSession().getAttribute("error") != null) { %>
        <div class="alert alert-danger">
            <%=request.getSession().getAttribute("error") %>
            <% request.getSession().removeAttribute("error"); %>
        </div>
    <% } %>
    <input type="text" name="username">
    <input type="password" name="password">
    <button>Login</button>
</form>
</body>
</html>

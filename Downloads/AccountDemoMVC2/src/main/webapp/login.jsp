<%-- 
    Document   : login
    Created on : Sep 16, 2024, 12:15:23 AM
    Author     : Jackt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Page</h2>
    <form action="Login" method="POST">
        Username: <input type="text" name="username" /><br>
        Password: <input type="password" name="password" /><br>
        <input type="submit" value="Login" />
    </form>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<p style='color:red;'>" + message + "</p>");
        }
    %>
</body>
</html>


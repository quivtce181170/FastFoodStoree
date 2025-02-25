<%-- 
    Document   : welcome
    Created on : Sep 16, 2024, 12:15:31 AM
    Author     : Jackt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome Page</h2>
    <%
        // Lấy tên người dùng từ session
        String username = (String) session.getAttribute("username");
        if (username != null) {
            out.println("Welcome, " + username + "!");
        } else {
            // Nếu session hết hạn hoặc chưa đăng nhập, chuyển hướng lại trang login
            response.sendRedirect("login.jsp");
        }
    %>
    <br><br>
    <a href="logout.jsp">Logout</a>
</body>
</html>
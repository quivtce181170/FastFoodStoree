<%-- 
    Document   : logout
    Created on : Sep 16, 2024, 12:15:40 AM
    Author     : Jackt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>

    <body>
        <%
            session.invalidate(); // Hủy session hiện tại
            response.sendRedirect("login.jsp"); // Quay lại trang đăng nhập
        %>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Account" %>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null) {
        response.sendRedirect("LoginView.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 50px;
        }
        .container {
            max-width: 600px;
            background: white;
            padding: 20px;
            margin: auto;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
        .info {
            text-align: left;
            margin-top: 20px;
        }
        .logout {
            background-color: #ff4b5c;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .logout:hover {
            background-color: #d43848;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Chào mừng, <%= account.getFullName() != null ? account.getFullName() : account.getUsername() %>!</h2>
        <div class="info">
            <p><strong>Email:</strong> <%= account.getEmail() %></p>
            <p><strong>Số điện thoại:</strong> <%= account.getPhoneNumber() != null ? account.getPhoneNumber() : "Chưa cập nhật" %></p>
            <p><strong>Địa chỉ:</strong> <%= account.getAddress() != null ? account.getAddress() : "Chưa cập nhật" %></p>
            <p><strong>Vai trò:</strong> <%= account.getRole() %></p>
        </div>
        <br>
        <a href="LogoutController" class="logout">Đăng xuất</a>
    </div>

</body>
</html>

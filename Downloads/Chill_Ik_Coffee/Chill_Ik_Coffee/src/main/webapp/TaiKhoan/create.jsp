<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                background-color: #f5f5f5;
            }
            h1 {
                color: #333;
                font-size: 2.2em;
                margin: 20px 0;
            }
            form {
                background: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 50%;
                max-width: 500px;
            }
            label {
                font-weight: bold;
                display: block;
                margin-bottom: 8px;
                margin-top: 15px;
            }
            input[type="text"], input[type="password"], input[type="checkbox"] {
                width: 100%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            input[type="submit"] {
                background-color: #28a745;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 1em;
            }
            input[type="submit"]:hover {
                background-color: #218838;
            }
            .back-btn {

                margin-top: 15px;
                font-size: 14px;
                color: #6a82fb;
                text-decoration: none;
                display: inline-flex;
                align-items: center;
                cursor: pointer;
                background-color: transparent;
                border: none;
                outline: none;
                transition: color 0.3s ease;
            }

            .back-btn:hover {
                color: #fc5c7d;
            }

            .back-btn svg {
                margin-right: 5px;
            }
        </style>
    </head>
    <body>

        <h1>Create New Account</h1>
        <p class="text-danger">${errorMessage}</p> 
        <form action="<c:url value='/QuanliTaiKhoan'/>" method="post">
            <input type="hidden" name="action" value="create">

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="isNhanVien">Is Employee:</label>
            <input type="checkbox" id="isNhanVien" name="isNhanVien">

            <label for="isAdmin">Is Admin:</label>
            <input type="checkbox" id="isAdmin" name="isAdmin">

            
            <input type="submit" value="Create Account">
            <a href="<c:url value='/QuanliTaiKhoan'/>" class="back-btn">Back</a>
        </form>

    </body>
</html>
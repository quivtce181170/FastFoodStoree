<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Login - Coffee Shop</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&family=Roboto:wght@400;500&display=swap">
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                background-color: #f8f4f1;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-image: linear-gradient(to bottom right, #d7ccc8, #8d6e63);
                font-family: 'Roboto', sans-serif;
            }

            .login-container {
                background-color: #fff8f0;
                padding: 40px;
                border-radius: 15px;
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
                width: 350px;
                text-align: center;
                transition: transform 0.3s ease-in-out;
            }

            .login-container:hover {
                transform: scale(1.02);
            }

            h1 {
                font-family: 'Playfair Display', serif;
                font-size: 28px;
                color: #4e342e;
                font-weight: bold;
                margin-bottom: 20px;
            }

            .input-group {
                margin-bottom: 18px;
                text-align: left;
            }

            .input-group label {
                display: block;
                margin-bottom: 5px;
                font-size: 15px;
                color: #5d4037;
            }

            .input-group input {
                width: 100%;
                padding: 10px;
                border: 1px solid #d7ccc8;
                border-radius: 5px;
                font-size: 16px;
                transition: border-color 0.3s;
                font-family: 'Roboto', sans-serif;
            }

            .input-group input:focus {
                border-color: #8d6e63;
                outline: none;
            }

            .button-group {
                margin-top: 20px;
            }

            .btn {
                width: 100%;
                padding: 12px;
                font-size: 16px;
                color: #fff;
                background-color: #6d4c41;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease, box-shadow 0.3s ease;
                font-family: 'Roboto', sans-serif;
                font-weight: 500;
            }

            .btn:hover {
                background-color: #5d4037;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .back-btn {
                margin-top: 15px;
                font-size: 14px;
                color: #8d6e63;
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
                color: #4e342e;
            }

            .back-btn svg {
                margin-right: 5px;
            }

            .text-danger {
                color: #c62828;
                margin-top: 10px;
                margin-bottom: 10px;
                font-size: 14px;
                font-family: 'Roboto', sans-serif;
            }
            .button-register a{

                text-decoration: none;

            }
            .button-register {
                margin-top: 20px ;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h1>Login to Coffee Shop</h1>
            <p class="text-danger">${mess}</p>
            <form action="${pageContext.request.contextPath}/login" method="POST">
                <div class="input-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="Enter username" required>
                </div>
                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                </div>
                <div class="button-group">
                    <button type="submit" name="role" value="user" class="btn">Login</button>
                </div>
                <div>
                    <p class="button-register">Not Registered? <a href="<c:url value="/register"/>">Create An Account</a></p>  
                </div>
            </form>
            <button class="back-btn" onclick="window.location.href = '${pageContext.request.contextPath}/Quannuoc/Giaodien.jsp'" id="back">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" width="20" height="20">
                <path d="M11 19l-7-7 7-7" stroke="#8d6e63" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                Back
            </button>
        </div>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký Tài khoản</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Arial', sans-serif;
            }

            body {
                background-color: #f4e1d2; /* Light brown background */
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-image: linear-gradient(to bottom right, #c4a78a, #8e6f56); /* Coffee-inspired gradient */
                font-family: 'Roboto', sans-serif;
            }

            .register-container {
                background-color: #fff5e1; /* Light cream background */
                padding: 40px;
                border-radius: 15px;
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
                width: 350px;
                text-align: center;
                transition: transform 0.3s ease-in-out;
            }

            .register-container:hover {
                transform: scale(1.02);
            }

            h1 {
                margin-bottom: 30px;
                font-size: 28px;
                color: #6e4b3a; /* Dark brown text */
                font-weight: bold;
            }

            .input-group {
                margin-bottom: 20px;
                text-align: left;
            }

            .input-group label {
                display: block;
                margin-bottom: 8px;
                font-size: 14px;
                color: #6e4b3a; /* Dark brown text */
            }

            .input-group input {
                width: 100%;
                padding: 12px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
                transition: border-color 0.3s;
                background-color: #fff; /* White background for inputs */
            }

            .input-group input:focus {
                border-color: #8e6f56; /* Darker brown for focused input */
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
                background-color: #8e6f56; /* Coffee brown button */
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease, box-shadow 0.3s ease;
            }

            .btn:hover {
                background-color: #6a4d3b; /* Darker coffee brown on hover */
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .back-btn {
                margin-top: 15px;
                font-size: 14px;
                color: #6a82fb; /* Blue for contrast */
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

            .text-danger {
                color: red;
                margin-top: 10px;
                margin-bottom: 10px;
                font-size: 14px;
            }
        </style>
    </head>
    <body>
        <div class="register-container">
            <h1>Register Account</h1>
            <p class="text-danger">${mess}</p>
            <form action="${pageContext.request.contextPath}/register" method="POST">
                <div class="input-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="Enter username" required>
                </div>
                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                </div>
                <div class="input-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Re-enter your password" required>
                </div>
                <div class="button-group">
                    <button  type="submit" class="btn">Create</button>
                </div>
            </form>
            <button class="back-btn" onclick="window.location.href = '${pageContext.request.contextPath}/Quannuoc/Giaodien.jsp'" id="back">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" width="20" height="20">
                <path d="M11 19l-7-7 7-7" stroke="#6a82fb" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                Back
            </button>
        </div>
    </body>
</html>

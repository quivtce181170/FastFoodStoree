<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Registration</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 0;
                color: #333;
                background-image: url('https://img4.thuthuatphanmem.vn/uploads/2020/12/25/background-ca-phe-chuyen-nghiep-nhat_034336848.jpg');
                background-size: cover;
                background-position: center;
                background-attachment: fixed; /* Keep background fixed while scrolling */
            }
            h1 {
                color: #fff;
                text-align: center;
                margin-top: 50px;
                font-size: 36px;
            }
            form {
                background-color: rgba(255, 255, 255, 0.9); /* Trong suốt với độ mờ 90% */
                width: 50%;
                margin: 30px auto;
                padding: 40px;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
            }
            form div {
                margin-bottom: 20px;
            }
            label {
                display: inline-block;
                width: 150px;
                font-weight: bold;
                margin-bottom: 5px;
            }
            input[type="text"], input[type="date"], input[type="tel"] {
                width: calc(100% - 16px); /* Tính toán lại độ rộng */
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 5px;
                margin-top: 5px;
                font-size: 14px;
                transition: border-color 0.3s;
            }
            input[type="text"]:focus, input[type="date"]:focus, input[type="tel"]:focus {
                border-color: #28a745;
                outline: none;
            }
            button {
                padding: 12px 20px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                width: 100%;
                margin-top: 10px;
                transition: background-color 0.3s;
            }
            button:hover {
                background-color: #218838;
            }
            .back-button {
                background-color: #f0f0f0;
                color: #333;
                border: 1px solid #ddd;
                width: auto;
                cursor: pointer;
                margin-top: 10px;
                padding: 10px 20px;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .back-button:hover {
                background-color: #e9e9e9;
            }
            .message {
                text-align: center;
                color: red;
                font-size: 14px;
                margin-top: 10px;
            }
            footer {
                background-color: #333;
                color: white;
                text-align: center;
                padding: 10px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Employee Registration</h1>
        <form action="${pageContext.request.contextPath}/DonDK" method="POST">
            <div>
                <label for="tenNV">Employee Name:</label>
                <input type="text" id="tenNV" name="tenNV" required>
            </div>
            <div>
                <label for="Sdt">Phone Number:</label>
                <input type="tel" id="Sdt" name="Sdt" required pattern="[0-9]+" title="Please enter numbers only">
            </div>
            <div>
                <label for="diachi">Address:</label>
                <input type="text" id="diachi" name="diachi" required>
            </div>
            <div>
                <label for="ngaysinh">Date of Birth:</label>
                <input type="date" id="ngaysinh" name="ngaysinh" required>
            </div>
            <div>
                <label for="quequan">Hometown:</label>
                <input type="text" id="quequan" name="quequan" required>
            </div>
            <div>
                <button type="submit">Register</button>
                <button type="button" class="back-button" onclick="window.location.href = '${pageContext.request.contextPath}/Quannuoc/Giaodien.jsp'">Go Back</button>
            </div>
        </form>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <footer>
        <p>&copy; 2024 Chill Ik Coffee</p>
    </footer>
</body>
</html>

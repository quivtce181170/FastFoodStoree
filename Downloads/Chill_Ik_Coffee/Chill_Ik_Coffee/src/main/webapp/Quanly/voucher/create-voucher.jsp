<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Voucher</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            /* Thêm phông chữ từ Google Fonts */
            @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

            body {
                font-family: 'Roboto', sans-serif;
                margin: 20px;
                background-color: #f4f4f4; /* Nền sáng hơn */
            }

            nav {
                background-color: #4a4a4a; /* Màu xám đậm cho navigation */
                padding: 15px 30px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                color: white;
                border-radius: 5px; /* Góc bo tròn */
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* Bóng cho navigation */
            }

            nav a {
                color: white; /* Đặt màu chữ cho các liên kết là trắng */
                text-decoration: none; /* Bỏ gạch chân */
            }

            .logo {
                font-size: 1.8em; /* Tăng kích thước cho logo */
                font-weight: bold; /* Làm cho logo đậm hơn */
            }

            h1 {
                color: #007bff; /* Màu chữ cho tiêu đề Create New Voucher */
                text-align: center;
                font-size: 2.5em; /* Tăng kích thước chữ cho tiêu đề */
                margin: 40px 0; /* Khoảng cách trên dưới cho tiêu đề */
                text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1); /* Thêm hiệu ứng bóng cho tiêu đề */
            }

            /* Form tạo voucher */
            form div {
                margin-bottom: 15px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input[type="text"], input[type="number"], input[type="date"] {
                width: 100%;
                padding: 8px;
                border-radius: 5px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            .button {
                background-color: #b2ebf2; /* Màu nền xanh nhạt cho các nút */
                color: #000; /* Chữ màu đen */
                font-size: 1.2em;
                padding: 10px 20px; /* Thay đổi padding để nút lớn hơn */
                margin: 10px;
                border: 1px solid #000; /* Đường viền màu đen */
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s; /* Thêm hiệu ứng chuyển màu */
            }

            .button:hover {
                background-color: #80deea; /* Màu xanh dương đậm khi hover */
            }

            .button-container {
                display: flex;
                justify-content: flex-start; /* Đặt khoảng cách giữa các nút gần nhau */
                margin-top: 10px;
            }

            .error {
                color: red;
            }

            /* Hiệu ứng hover cho hàng trong bảng */
            .table tbody tr:hover {
                background-color: #f1f1f1; /* Nền sáng hơn khi hover vào hàng */
            }

            /* Reset */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
        </style>
    </head>
    <body>
        <nav>
            <a href="${pageContext.request.contextPath}/Quanly/voucher/ListVoucher" class="logo">Voucher Management</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/Quanly/voucher/ListVoucher">Voucher</a></li>
                <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
            </ul>
        </nav>

        <h1>Create New Voucher</h1>

        <p style="color: red;">
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
        </p>

        <form method="post" action="CreateVoucher">
            <div>
                <label for="name">Voucher Name</label>
                <input type="text" id="name" name="name" required placeholder="Enter voucher name">
            </div>
            <div>
                <label for="discount">Discount (%)</label>
                <input type="number" id="discount" name="discount" required min="0" max="100" 
                       placeholder="Enter discount percentage">
            </div>
            <div>
                <label for="quantity">Quantity</label> <!-- Thêm trường nhập số lượng -->
                <input type="number" id="quantity" name="quantity" required min="1" max="99" 
                       placeholder="Enter quantity (1-99)">
            </div>
            <div>
                <label for="expiry_date">Expiry Date</label>
                <input type="date" id="expiry_date" name="expiry_date" required>
            </div>
            <div class="button-container">
                <button type="button" class="button" onclick="window.location.href = '<%= request.getContextPath()%>/Quanly/voucher/ListVoucher'" id="back">Back</button>
                <button type="submit" class="button" id="submit" style="margin-left: 10px;">Create</button>
            </div>
        </form>
    </body>
</html>

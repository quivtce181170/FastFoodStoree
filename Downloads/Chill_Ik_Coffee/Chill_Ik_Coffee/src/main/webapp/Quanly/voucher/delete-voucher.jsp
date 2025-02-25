<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Voucher</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Thêm phông chữ từ Google Fonts */
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

        body {
            font-family: 'Roboto', sans-serif; /* Đặt phông chữ Roboto */
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
            color: #007bff; /* Màu chữ cho tiêu đề Delete Voucher */
            text-align: center;
            font-size: 2.5em; /* Tăng kích thước chữ cho tiêu đề */
            margin: 40px 0; /* Khoảng cách trên dưới cho tiêu đề */
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1); /* Thêm hiệu ứng bóng cho tiêu đề */
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

        .error {
            color: red;
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

    <h1>Delete Voucher</h1>

    <c:if test="${not empty selectedVoucher}">
        <p>Are you sure you want to delete the voucher <b>${selectedVoucher.name}</b> with ID <b>${selectedVoucher.id}</b>?</p>
        <form method="post" action="DeleteVoucher">
            <input type="hidden" name="id" value="${selectedVoucher.id}"/>
            <a href="<c:url value='/Quanly/voucher/ListVoucher'/>" id="back" class="button back-button">Back</a>
            <input id="submit" type="submit" value="Delete" class="button delete-button"/>
        </form>
    </c:if>

    <c:if test="${empty selectedVoucher}">
        <p>No voucher selected for deletion.</p>
        <a href="<c:url value='/Quanly/voucher/ListVoucher'/>" class="button back-button">Back to List</a>
    </c:if>
</body>
</html>

<%-- 
    Document   : edit_monnuoc_quanly
    Created on : Oct 30, 2024, 3:28:56 PM
    Author     : Nguyen Ngoc Phat - CE180321
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit drink</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px;
                background-color: cornsilk;
                margin-bottom: 70px;
                height: 110px;
            }

            h1 {
                text-align: center;
                color: #333;
            }
            form {
                background-color: white; /* Thêm nền trắng cho form */
                padding: 20px;
                border-radius: 5px; /* Bo góc cho form */
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Thêm hiệu ứng đổ bóng */
            }

            form div {
                margin-bottom: 15px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input[type="text"], input[type="number"] {
                width: 100%;
                padding: 10px; /* Thêm padding cho ô nhập liệu */
                border-radius: 5px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            input[type="checkbox"] {
                width: 20px;
                height: 20px;
                margin-right: 10px; /* Thêm khoảng cách bên phải */
                vertical-align: middle; /* Căn giữa với nhãn */
            }
            button {
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                background-color: #5cb85c;
                color: white;
                cursor: pointer;
            }
            button[type="button"] {
                background-color: #007bff;
            }
            button:hover {
                opacity: 0.8;
            }
            p {
                text-align: center;
                color: red;
            }

            .navbar {
                position: relative;
                display: inline-block;
                vertical-align: middle;
                line-height: 0.7;
                text-align: center;
                margin-left: 900px;
                background-color: blanchedalmond;
                border-radius: 10px;
                width: 130px;
                height: 30px;
                text-decoration: none;
                border: 1px solid black;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                color: #333;
                cursor: pointer;
                transition: box-shadow 0.3s ease;
                font-weight: bold;
            }
            .navbar a {
                text-decoration: none;
                font-weight: bold;
                color: #333;
                padding: 10px 20px;
                display: inline-block;
                text-align: center;
            }

            .navbar .dropdown {
                display: none;
                position: absolute;
                background-color: blanchedalmond;
                min-width: 120px;
                box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
                z-index: 1;
                max-width: 100%; /* Giới hạn chiều rộng của menu */
                overflow: hidden; /* Đảm bảo menu không tràn */
                box-sizing: border-box;
            }

            .navbar .dropdown a {
                display: block;
                padding: 8px 10px;
                text-decoration: none;
                color: #333;
            }

            .navbar:hover .dropdown {
                display: block;
            }

            .dropdown a:hover {
                background-color: #f1f1f1;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h1>Chill Ik Coffee</h1>
            <div class="navbar">
                <a href="#">Home</a>
                <div class="dropdown">
                    <a href="<c:url value="/"/>">Home</a>
                    <a href="<c:url value="/Menu"/>">Menu</a>
                    <c:if test="${empty sessionScope.user}">
                        <a href="<c:url value="/login"/>">Login</a>
                        <a href="<c:url value="/register"/>">Register</a>
                    </c:if>
                    <c:if test="${not empty sessionScope.user}">
                        <a href="<c:url value="/Logout" />">Logout</a>
                    </c:if>
                </div>
            </div>
        </div>
        <h1>Edit Drink</h1>

        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <c:choose>
            <c:when test="${not empty drink}">
                <form action="<c:url value='/EditDrink'/>" method="post">
                    <c:choose>
                        <c:when test="${sessionScope.user.isAdmin}">
                            <div>
                                <label for="id">Drink ID</label>
                                <input type="text" id="id" name="id" value="${drink.id}" readonly />
                            </div>
                            <div>
                                <label for="name">Drink Name</label>
                                <input type="text" id="name" name="name" value="${drink.name}" required />
                            </div>
                            <div>
                                <label for="gia">Price</label>
                                <input type="number" id="gia" name="gia" value="${drink.gia}" required 
                                       min="0" max="100000000">
                            </div>
                            <div>
                                <label for="so_luong">Quantity</label>
                                <input type="number" id="so_luong" name="so_luong" value="${drink.soluong}" required 
                                       min="0" max="100000000">
                            </div>
                            <div>
                                <label for="loai_nuoc_id">Drink Type(ID)</label>
                                <input type="number" id="loai_nuoc_id" name="loai_nuoc_id" value="${drink.loainuocid}" required 
                                       min="1" max="4">
                            </div>
                            <div>
                                <label for="imageUrl">Image URL</label>
                                <input type="text" id="imageUrl" name="imageUrl" value="${drink.imageUrl}" required>
                            </div>
                            <div>
                                <label for="trang_thai">Status(Available)<input type="checkbox" id="trang_thai" name="trang_thai" ${drink.trangthai ? 'checked' : ''} /></label>
                            </div>
                            <div>
                                <button type="button" id="back" onclick="window.location.href = '<c:url value='/Menu' />'">Back</button>
                                <button type="submit" id="submit">Edit</button>
                            </div>
                        </c:when>
                        <c:when test="${sessionScope.user.isNhanVien}">
                            <div>
                                <label for="id">Drink ID</label>
                                <input type="text" id="id" name="id" value="${drink.id}" readonly />
                            </div>
                            <div>
                                <label for="name">Drink Name</label>
                                <input type="text" id="name" name="name" value="${drink.name}" readonly />
                            </div>
                            <div>
                                <label for="gia">Price</label>
                                <input type="number" id="gia" name="gia" value="${drink.gia}" readonly 
                                       min="0" max="100000000">
                            </div>
                            <div>
                                <label for="so_luong">Quantity</label>
                                <input type="number" id="so_luong" name="so_luong" value="${drink.soluong}" readonly 
                                       min="0" max="100000000">
                            </div>
                            <div>
                                <label for="loai_nuoc_id">Drink Type(ID)</label>
                                <input type="number" id="loai_nuoc_id" name="loai_nuoc_id" value="${drink.loainuocid}" readonly 
                                       min="1" max="4">
                            </div>
                            <div>
                                <label for="imageUrl">Image URL</label>
                                <input type="text" id="imageUrl" name="imageUrl" value="${drink.imageUrl}" readonly>
                            </div>
                            <div>
                                <label for="trang_thai">Status(Available)<input type="checkbox" id="trang_thai" name="trang_thai" ${drink.trangthai ? 'checked' : ''} /></label>
                            </div>
                            <div>
                                <button type="button" id="back" onclick="window.location.href = '<c:url value='/Menu' />'">Back</button>
                                <button type="submit" id="submit">Edit</button>
                            </div>
                        </c:when>
                    </c:choose>
                </form>
            </c:when>

        </c:choose>

    </body>
</html>
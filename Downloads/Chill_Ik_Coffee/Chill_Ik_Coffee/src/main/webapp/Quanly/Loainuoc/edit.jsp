<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="model.LoaiNuoc"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Drink Type</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f5f5f5;
            }
            h1 {
                text-align: center;
                color: #7B4B3A;
                font-weight: bold;
                font-size: 3em;
                text-transform: uppercase;
                margin-top: 30px;
                text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
            }
            .container {
                margin-top: 50px;
            }
            .alert-danger {
                color: #FFFFFF;
                background-color: #C65D5D;
                border-color: #C65D5D;
                text-align: center;
            }
            label {
                font-weight: bold;
                color: #8C6A4B;
                font-size: 1.2em;
            }
            #loai_nuoc {
                background-color: #ffffff; /* Nền trắng cho ô nhập liệu */
                color: #7B4B3A; /* Màu nâu đậm cho chữ */
                border: 1px solid #8C6A4B;
            }
            .btn-primary {
                background-color: #7B4B3A;
                border: none;
                color: #ffffff;
            }
            .btn-secondary {
                background-color: #C65D5D; /* Màu đỏ nhạt cho nút back */
                border: none;
                color: #ffffff;
            }
            .btn-primary:hover, .btn-secondary:hover {
                opacity: 0.9;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Edit Drink Type</h1>
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:when>
            </c:choose>
            <form action="<c:url value='/Quanly/Loainuoc/EditLoaiNuoc'/>" method="POST">
                <input type="hidden" name="id" value="${loaiNuoc.id}" />
                <div class="form-group">
                    <label for="loai_nuoc">Drink Type:</label>
                    <input type="text" class="form-control" id="loai_nuoc" name="loai_nuoc" value="${loaiNuoc.loaiNuoc}" required />
                </div>
                <a href="<c:url value='/Quanly/Loainuoc/ListLoaiNuoc'/>" class="btn btn-secondary">Back</a>
                <button type="submit" class="btn btn-primary">Update</button>
            </form>
        </div>
    </body>
</html>
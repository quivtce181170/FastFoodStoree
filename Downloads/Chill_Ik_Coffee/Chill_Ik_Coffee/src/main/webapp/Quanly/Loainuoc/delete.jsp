<%@page import="model.LoaiNuoc"%>
<%@page import="DAO.LoaiNuocDAO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="db.DBContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Drink Type</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f5f5f5; /* Màu nền trang */
            }
            h1 {
                text-align: center;
                color: #7B4B3A; /* Màu nâu ấm */
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
                background-color: #C65D5D; /* Màu đỏ nhạt */
                border-color: #C65D5D;
                text-align: center;
            }
            .confirmation-text {
                text-align: center;
                font-size: 1.5em; /* Tăng kích thước chữ */
                color: #8C6A4B; /* Màu nâu đậm */
                font-weight: bold; /* Chữ đậm */
                margin-top: 20px;
                text-shadow: 0.5px 0.5px 1px rgba(0, 0, 0, 0.2); /* Hiệu ứng bóng nhẹ */
            }
            .loai_nuoc {
                font-weight: bold;
                color: #7B4B3A; /* Màu nâu ấm để nổi bật */
            }
            .loai_nuoc_id {
                font-weight: bold;
                color: #8C6A4B; /* Màu nâu nhạt */
            }
            .btn-danger {
                background-color: #C65D5D; /* Màu đỏ nhạt cho nút delete */
                border: none;
                color: #ffffff;
            }
            .btn-secondary {
                background-color: #8C6A4B; /* Màu nâu đậm cho nút back */
                border: none;
                color: #ffffff;
            }
            .btn-danger:hover, .btn-secondary:hover {
                opacity: 0.9;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Delete Drink Type</h1>
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <p class="alert alert-danger">${errorMessage}</p>
                </c:when>
                <c:otherwise>
                    <form method="POST" action="<c:url value='/Quanly/Loainuoc/DeleteLoaiNuoc'/>">          
                        <div class="confirmation-text">
                            Are you sure you want to delete the drink type <span class="loai_nuoc">${loaiNuoc.loaiNuoc}</span> with ID <span class="loai_nuoc_id">${loaiNuoc.id}</span>?
                        </div>
                        <input type="hidden" name="id" value="${loaiNuoc.id}"/>
                        <button type="submit" class="btn btn-danger mt-3">Delete</button>
                        <a href="<c:url value='/Quanly/Loainuoc/ListLoaiNuoc'/>" class="btn btn-secondary mt-3">Back</a>         
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>      
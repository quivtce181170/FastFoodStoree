<%@page import="model.LoaiNuoc"%>
<%@page import="DAO.LoaiNuocDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create New Drink Type</title>
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
            .form-group label {
                color: #7B4B3A;
                font-weight: bold;
                font-size: 1.25em;
                text-shadow: 0.5px 0.5px 1px rgba(0, 0, 0, 0.2);
            }
            .form-control {
                background-color: #ffffff; /* Màu nền trắng cho ô nhập liệu */
                color: #7B4B3A; /* Màu chữ nâu */
                border: 1px solid #8C6A4B;
            }
            .btn-primary {
                background-color: #8C6A4B; /* Nút create màu nâu */
                border: none;
                color: #ffffff;
            }
            .btn-secondary {
                background-color: #C65D5D; /* Nút back màu đỏ nhạt */
                border: none;
                color: #ffffff;
            }
            .btn-primary:hover, .btn-secondary:hover {
                opacity: 0.9;
            }
        </style>
    </head>
    <body>
        <h1>Create New Drink Type</h1>

        <div class="container">       
            <form action="<c:url value='/Quanly/Loainuoc/CreateLoaiNuoc'/>" method="POST">
                <div class="form-group">
                    <label for="name">Drink Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <a href="<c:url value='/Quanly/Loainuoc/ListLoaiNuoc'/>" class="btn btn-secondary" id="back">Back</a>
                <button type="submit" class="btn btn-primary" id="submit">Create</button>
            </form>
        </div>    
    </body>
</html>
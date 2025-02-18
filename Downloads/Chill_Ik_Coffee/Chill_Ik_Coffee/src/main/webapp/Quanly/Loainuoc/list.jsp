<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.LoaiNuocDAO"%>
<%@page import="model.LoaiNuoc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Drink Types</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f5f5f5;
            }
            h1 {
                text-align: center;
                color: #7B4B3A; /* Warm brown color */
                font-weight: bold;
                font-size: 3em;
                text-transform: uppercase;
                margin-top: 30px;
                text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
            }
            .create-button {
                margin-bottom: 20px; /* Adjust spacing as needed */
            }
            .table {
                background-color: #fff; /* White background for the table */
                border-radius: 8px; /* Rounded corners for the table */
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            th {
                background-color: #d9c8a3; /* Light yellow background for table headers */
                color: #4B3D32; /* Dark brown color for text */
            }
            td {
                color: #4B3D32; /* Dark brown color for text */
            }
            .btn-primary {
                background-color: #8C6A4B; /* Brown color for edit button */
                border: none;
            }
            .btn-danger {
                background-color: #C65D5D; /* Light red color for delete button */
                border: none;
            }
            .btn-success {
                background-color: #7B4B3A; /* Dark brown color for new create button */
                color: #ffffff; /* White text for contrast */
                border: none;
            }
        </style>
    </head>
    <body>
        <h1>List of Drink Types</h1>
        <div class="container">           
            <a href="<c:url value='/Quanly/Loainuoc/CreateLoaiNuoc'/>" class="btn btn-success mt-3 mb-3 create-button">Create New</a>

            <c:choose>
                <c:when test="${list == null || list.isEmpty()}">
                    <div>
                        No drink types available. Please create a new one!
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Drink Type</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="ln"> 
                                <tr>
                                    <td>${ln.id}</td>
                                    <td>${ln.loaiNuoc}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/Quanly/Loainuoc/EditLoaiNuoc?id=${ln.id}" class="btn btn-primary">Edit</a>
                                        <a href="${pageContext.request.contextPath}/Quanly/Loainuoc/DeleteLoaiNuoc?id=${ln.id}" class="btn btn-danger">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
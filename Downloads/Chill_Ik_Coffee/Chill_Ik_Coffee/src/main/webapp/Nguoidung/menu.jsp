<%-- 
    Document   : menu
    Created on : Oct 28, 2024, 11:34:26 PM
    Author     : Nguyen Ngoc Phat - CE180321
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Drink"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DrinkDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chill Ik Coffee</title>
        <style>
            html, body {
                margin: 0;
                padding: 0;
                height: 100%;
                overflow-x: hidden;
            }
            body {
                font-family: Arial, sans-serif;
                background-image: url('https://img4.thuthuatphanmem.vn/uploads/2020/12/25/background-ca-phe-chuyen-nghiep-nhat_034336848.jpg');
                background-size: cover;
                background-position: center;
                background-attachment: fixed;
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 20px;
                background-color: cornsilk;
                margin-bottom: 70px;
                height: 50px;
            }
            .header img {
                height: 10vh;
                margin-left: 20px;
            }
            .navbar {
                position: relative;
                display: block;
                align-items: center;
                justify-content: center;
                background-color: #FFE5B4; /* Màu nền nhẹ nhàng */
                border-radius: 12px; /* Làm mềm các góc */
                width: 160px;
                height: 40px;
                text-align: center;
                border: 1px solid #D4A373;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); /* Giảm độ đậm của bóng đổ */
                color: #333;
                cursor: pointer;
                transition: box-shadow 0.3s ease, background-color 0.3s ease;
                font-weight: bold;
                font-size: 16px;
                margin-left: auto; /* Căn phải */
                margin-right: 17px; /* Tạo khoảng cách bên phải */
            }

            .navbar a {
                text-decoration: none;
                font-weight: bold;
                color: #333;
                padding: 8px 16px; /* Tăng kích thước để căn giữa nội dung */
                display: inline-block;
                text-align: center;
            }

            /* Hiệu ứng khi hover */
            .navbar:hover {
                background-color: #FFDAB9;
                box-shadow: 0 6px 16px rgba(0, 0, 0, 0.25); /* Bóng đổ mạnh hơn khi hover */
            }

            /* Hiệu ứng khi nhấn vào */
            .navbar:active {
                background-color: #FFC07F;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                transform: translateY(1px);
            }

            .menu-container {
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                gap: 1.5rem;
                padding: 20px;
            }
            .menu-item {
                width: 200px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                overflow: hidden;
                text-align: center;
            }
            .menu-info {
                padding: 10px;
            }
            footer {
                background-color: cornsilk;
                text-align: center;
                padding: 10px 0;
                width: 100%;
            }

            .btnAddToCart {
                margin: 16px;
                background-color: blanchedalmond;
                border-radius: 10px;
                width: 150px;
                height: 30px;
                cursor: pointer;
                transition: box-shadow 0.3s ease;
                font-weight: bold;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                border: 1px solid black;
            }

            .btnCart {
                display: inline-flex;
                align-items: center;
                justify-content: center;
                margin: 16px;
                background-color: #FFE5B4;
                border-radius: 12px;
                width: 160px;
                height: 40px;
                text-decoration: none;
                text-align: center; /* Căn chữ vào giữa */
                padding: 0; /* Đảm bảo không có khoảng trống thừa */
                border: 1px solid #D4A373;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
                color: #333;
                cursor: pointer;
                transition: transform 0.2s ease, box-shadow 0.3s ease;
                font-weight: bold;
                font-size: 16px;
            }

            .btnCart a {
                text-decoration: none; /* Removes the underline */
                color: inherit; /* Inherits the text color from the parent .btnCart element */
            }

            /* Hiệu ứng khi hover */
            .btnCart:hover {
                background-color: #FFDAB9;
                color: #333;
                box-shadow: 0 6px 16px rgba(0, 0, 0, 0.25);
                transform: translateY(-2px);
            }

            /* Hiệu ứng khi nhấn vào */
            .btnCart:active {
                background-color: #FFC07F;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                transform: translateY(1px);
            }



            .navbar .dropdown {
                display: none;
                position: absolute;
                background-color: cornsilk;
                min-width: 160px;
                box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
                z-index: 1;
                max-width: 100%;
                overflow: hidden;
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

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th {
                text-align: center;
                padding: 10px;
                border-bottom: 2px solid black;
                font-weight: bold;
            }
            .n {
                text-align: left;
            }

            td {
                padding: 10px;
            }

            tr:nth-child(even) {
                background-color: #f4f4f9;
            }

            tr:nth-child(odd) {
                background-color: #ffffff;
            }

            .rented-checkbox {
                width: 20px;
                height: 20px;
                cursor: pointer;
            }

            h1 {
                margin-top: 20px;
                height: 25vh;
                display: flex;
                justify-content: flex-start;
                align-items: center;
                font-size: 2.5em;
                font-style: oblique;
                font-family: cursive;
                color: #a67c52;
            }

            a.create-btn {
                display: block;
                text-align: center;
                width: 100px;
                text-decoration: none;
                background-color: #28a745;
                color: white;
                padding: 10px 0;
                border-radius: 5px;
                margin-left: auto;
                margin-right: 20px;
            }

            a.create-btn:hover {
                background-color: #218838;
            }

            table, td {
                border: none;
            }

            .btn {
                color: white;
                padding: 5px 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
            }

            .btn-success {
                background-color: #28a745;
            }

            .btn-success:hover {
                background-color: #218838;
            }

            .btn-secondary {
                background-color: #007bff;
            }

            .btn-secondary:hover {
                background-color: #0056b3;
            }

            .btn-danger {
                background-color: #dc3545;
            }

            .btn-danger:hover {
                background-color: #c82333;
            }



            /* Header CSS */
            .headeradmin {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px;
                background-color: cornsilk;
                margin-bottom: 70px;
                height: 110px;

            }


            img {
                width: 200px;
                height: 180px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                overflow: hidden;
                text-align: center;
            }
        </style>

    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.user.isAdmin}">
                <div class="headeradmin">
                    <h1>Chill Ik Coffee</h1>
                    <div class="navbar">
                        <a href="#">Drink Types</a>
                        <div class="dropdown">
                            <a href="<c:url value="/Menu"/>">All</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=1'/>">Coffee</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=2'/>">Tea</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=3'/>">Smoothie</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=4'/>">Milk Tea</a>
                        </div>
                    </div>
                    <div class="navbar">
                        <a href="<c:url value="/Quanly/Loainuoc/ListLoaiNuoc"/>">Drinks Type</a>                       
                    </div>
                    <div class="navbar">
                        <a href="#">Home</a>
                        <div class="dropdown">
                            <a href="<c:url value="/Quannuoc/Giaodien.jsp"/>">Home</a>
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
                <a href="<c:url value="/CreateDrink" />" class="create-btn btn btn-success" id="btn">Create</a>
                <table>
                    <tr>
                        <th class="n">ID</th>
                        <th class="n">Drink name</th>
                        <th class="n">Price</th>
                        <th class="n">Quantity</th>
                        <th class="n">Water Type(ID)</th>
                        <th class="n">Status</th>
                        <th></th>
                    </tr>                   
                    <c:forEach items="${drinks}" var="d">
                        <tr>
                            <td>${d.id}</td>
                            <td>${d.name}</td>
                            <td>${d.gia}</td>
                            <td>${d.soluong}</td>
                            <td>${d.loainuocid}</td>
                            <td>${d.trangthai ? 'Available' : 'OUT OF STOCK'}</td>
                            <td>
                                <a href="<c:url value="/EditDrink" />?id=${d.id}" class="btn btn-secondary">Edit</a>
                                <a href="<c:url value="/DeleteDrink" />?id=${d.id}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>                   
                </table>
                <div class="menu-container">
                    <c:choose>
                        <c:when test="${totalPages > 1}">
                            <div class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage - 1}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn btn-primary">Previous</a>
                                    </c:if>
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage - 1}" class="btn btn-primary">Previous</a>
                                    </c:if>
                                </c:if>

                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${i}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                                    </c:if>
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${i}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                                    </c:if>
                                </c:forEach>

                                <c:if test="${currentPage < totalPages}">
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage + 1}" class="btn btn-primary">Next</a>
                                    </c:if>
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage + 1}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn btn-primary">Next</a>
                                    </c:if>
                                </c:if>
                            </div>
                        </c:when> 
                    </c:choose>
                </div>

            </c:when>
            <c:when test="${sessionScope.user.isNhanVien}">
                <div class="headeradmin">
                    <h1>Chill Ik Coffee</h1>
                    <div class="navbar">
                        <a href="#">Drink Types</a>
                        <div class="dropdown">
                            <a href="<c:url value="/Menu"/>">All</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=1'/>">Coffee</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=2'/>">Tea</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=3'/>">Smoothie</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=4'/>">Milk Tea</a>
                        </div>
                    </div>
                    <div class="navbar">
                        <a href="#">Home</a>
                        <div class="dropdown">
                            <a href="<c:url value="/"/>">Home</a>
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
                <table>
                    <tr>
                        <th class="n">ID</th>
                        <th class="n">Drink Name</th>
                        <th class="n">Price</th>
                        <th class="n">Quantity</th>
                        <th class="n">Water Type(ID)</th>
                        <th class="n">Status</th>
                        <th></th>
                    </tr>                   
                    <c:forEach items="${drinks}" var="d">
                        <tr>
                            <td>${d.id}</td>
                            <td>${d.name}</td>
                            <td>${d.gia}</td>
                            <td>${d.soluong}</td>
                            <td>${d.loainuocid}</td>
                            <td>${d.trangthai ? 'Available' : 'OUT OF STOCK'}</td>
                            <td>
                                <a href="<c:url value="/EditDrink" />?id=${d.id}" class="btn btn-secondary">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>                   
                </table>
                <div class="menu-container">
                    <c:choose>
                        <c:when test="${totalPages > 1}">
                            <div class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage - 1}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn btn-primary">Previous</a>
                                    </c:if>
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage - 1}" class="btn btn-primary">Previous</a>
                                    </c:if>
                                </c:if>

                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${i}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                                    </c:if>
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${i}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                                    </c:if>
                                </c:forEach>

                                <c:if test="${currentPage < totalPages}">
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage + 1}" class="btn btn-primary">Next</a>
                                    </c:if>
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage + 1}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn btn-primary">Next</a>
                                    </c:if>
                                </c:if>
                            </div>
                        </c:when> 
                    </c:choose>
                </div>

            </c:when>
            <c:otherwise>
                <div class="header">
                    <h1>Chill Ik Coffee</h1>
                    <div class="navbar">
                        <a href="#">Drink Types</a>
                        <div class="dropdown">
                            <a href="<c:url value="/Menu"/>">All</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=1'/>">Coffee</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=2'/>">Tea</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=3'/>">Smoothie</a>
                            <a href="<c:url value='/Menu?page=1&loai_nuoc_id=4'/>">Milk Tea</a>
                        </div>
                    </div>
                    <div class="navbar">
                        <a href="<c:url value='/PaymentUserList' />">Order placed</a>
                    </div>
                    <div class="navbar">
                        <a href="<c:url value='/cart' />">Shopping Cart</a>
                    </div>
                    <div class="navbar">
                        <a href="#">Home</a>
                        <div class="dropdown">
                            <a href="<c:url value="/"/>">Home</a>
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
                <div class="menu-container">
                    <c:if test="${empty drinks}">
                        <p>There are no products in the menu.</p>
                    </c:if>
                    <c:forEach items="${drinks}" var="d">
                        <div class="menu-item">
                            <img src="${d.imageUrl}" alt="${d.name} logo">
                            <h3>${d.name}</h3>
                            <p>Price: ${d.gia} VND</p>
                            <p>Status: ${d.trangthai ? 'Available' : 'OUT OF STOCK'}</p>                           
                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                <input type="hidden" name="productId" value="${d.id}" />
                                <label for="quantity" <c:if test="${!d.trangthai}">hidden</c:if> >Quantity</label>
                                <input type="number" name="quantity" min="1" value="0" required <c:if test="${!d.trangthai}">hidden</c:if> />
                                <input type="hidden" name="drinkId" value="${drink.id}" />
                                <input type="hidden" name="loai_nuoc_id" value="${param.loai_nuoc_id}" />
                                <input type="hidden" name="page" value="${param.page}" />
                                <button class="btnAddToCart" type="submit" <c:if test="${!d.trangthai}">hidden</c:if> >Add to cart</button>
                                </form>
                            </div>
                    </c:forEach>
                </div>
                <div class="menu-container">
                    <c:choose>
                        <c:when test="${totalPages > 1}">
                            <div class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage - 1}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn btn-primary">Previous</a>
                                    </c:if>
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage - 1}" class="btn btn-primary">Previous</a>
                                    </c:if>
                                </c:if>

                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${i}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                                    </c:if>
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${i}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                                    </c:if>
                                </c:forEach>

                                <c:if test="${currentPage < totalPages}">
                                    <c:if test="${empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage + 1}" class="btn btn-primary">Next</a>
                                    </c:if>
                                    <c:if test="${not empty param.loai_nuoc_id}">
                                        <a href="${pageContext.request.contextPath}/Menu?page=${currentPage + 1}&loai_nuoc_id=${param.loai_nuoc_id}" class="btn btn-primary">Next</a>
                                    </c:if>
                                </c:if>
                            </div>
                        </c:when> 
                    </c:choose>
                </div>
            </c:otherwise>
        </c:choose>
        <footer>
            <p>Address: GROUP 3 SE1806 | Phone number: 0584428016</p>
        </footer>
    </body>
</html>


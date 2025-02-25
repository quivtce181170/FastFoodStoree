<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="model.TaiKhoan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.TaiKhoanDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> List</title>
        <style>
            /* General Styles */
            body {
                padding-top: 70px;

                background-color: #f3e5d7; /* Light beige background for a warm feel */
                color: #4b3832; /* Dark brown text for readability */
            }

            h1 {
                margin-left: 20px;
                margin-top: 20px;
                font-size: 2.5em;
                color: #4b3832; /* Dark brown for titles */
            }

            table {
                width: 98%;
                border-collapse: collapse;
                margin: 10px auto;
                background-color: #fffaf3; /* Light cream background for the table */
                border-radius: 8px;
                overflow: hidden;
            }

            th, td {
                padding: 10px;
                text-align: center;
                border-bottom: 1px solid #d1b7a1; /* Light brown border for a soft look */
            }

            tr:nth-child(even) {
                background-color: #f8f1e9; /* Lighter beige for even rows */
            }

            tr:nth-child(odd) {
                background-color: #efe6db; /* Slightly darker beige for odd rows */
            }

            /* Button Styles */
            a.btn-success, a.btn-back, a.btn-primary, a.btn-secondary, a.btn-danger {
                color: white;
                padding: 10px 15px;
                border-radius: 5px;
                text-align: center;
                text-decoration: none;
                margin: 0 5px;
            }

            a.btn-success {
                background-color: #8b5e3c; /* Dark brown */
            }

            a.btn-success:hover {
                background-color: #7a5032;
            }

            a.btn-back {
                background-color: #d2b48c; /* Tan for a soft look */
                color: #4b3832;
            }

            a.btn-back:hover {
                background-color: #c2a07a;
            }

            a.btn-secondary {
                background-color: #a67c52; /* Medium brown */
            }

            a.btn-secondary:hover {
                background-color: #8f6740;
            }

            a.btn-danger {
                background-color: #cc6b49; /* Reddish-brown */
            }

            a.btn-danger:hover {
                background-color: #b85737;
            }

            /* Navigation Bar */
            nav {
                background-color: #4b3832; /* Dark brown */
                color: whitesmoke;
                padding: 10px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100%;
                position: fixed;
                top: 0;
                left: 0;
                z-index: 1000;
                transition: top 0.1s;
            }

            nav .logo {
                font-size: 1.8em;
                font-weight: bold;
                color: #f8f1e9; /* Light cream for logo text */
            }

            nav ul li a {
                color: #f8f1e9;
                padding: 8px 15px;
                border-radius: 5px;
            }

            nav ul li span {
                color: #f8f1e9;
            }

            /* Pagination */
            .pagination a {
                background-color: #a67c52; /* Medium brown for pagination buttons */
                color: #fffaf3; /* Light cream text color */
                padding: 8px 12px;
                margin: 0 3px;
                border-radius: 5px;
                text-decoration: none;
            }

            .pagination a.btn-primary {
                background-color: #8b5e3c; /* Dark brown for active or primary pagination */
            }

            .pagination a:hover {
                background-color: #7a5032; /* Darker brown on hover */
            }

        </style>
    </head>
    <body>
        <nav>
            <div class="logo">Chill Ik Coffee</div>
            <ul>

                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li>   <span>
                            <img src="https://png.pngtree.com/png-vector/20220225/ourmid/pngtree-black-coffee-png-image_4463597.png" alt="User Icon" style="width:16px; height:16px; margin-right:5px; vertical-align:middle;">
                            Hello, ${sessionScope.user.username}
                        </span> <a href="<c:url value='/Logout'/>">Logout</a> </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value='/Login'/>">Login</a></li>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </nav>

        <h1>List of accounts</h1>
        <a href="<c:url value='/QuanliTaiKhoan?TaiKhoan=create'/>" class="btn btn-success">Create</a>
        <a href="<c:url value='/trangchu'/>" class="btn btn-back">Back</a>

        <c:choose>
            <c:when test="${empty tk}">
                <div>There is no account yet. Please create a new one!</div>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th class="n">ID</th>
                        <th class="n">Username</th>
                        <th class="n">Password</th>
                        <th class="n">Employee</th>
                        <th class="n">Manager</th>
                        <th class="actions-col">Actions</th>
                    </tr>
                    <tbody>
                        <c:forEach items="${tk}" var="m"> 
                            <tr>
                                <td>${m.id}</td>
                                <td>${m.username}</td>
                                <td>${m.password}</td>
                                <td><input type="checkbox" ${m.isNhanVien ? "checked" : ""} disabled class="rented-checkbox"></td>
                                <td><input type="checkbox" ${m.isAdmin ? "checked" : ""} disabled class="rented-checkbox"></td>
                                <td>
                                    <a href="<c:url value='/QuanliTaiKhoan?TaiKhoan=edit'/>&id=${m.id}" class="btn btn-secondary">Edit</a>
                                    <a href="<c:url value='/QuanliTaiKhoan?TaiKhoan=delete'/>&id=${m.id}" class="btn btn-danger">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${totalPages > 1}">
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="${pageContext.request.contextPath}/QuanliTaiKhoan?TaiKhoan=list&page=${currentPage - 1}" class="btn btn-primary">Previous</a>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="${pageContext.request.contextPath}/QuanliTaiKhoan?TaiKhoan=list&page=${i}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="${pageContext.request.contextPath}/QuanliTaiKhoan?TaiKhoan=list&page=${currentPage + 1}" class="btn btn-primary">Next</a>
                    </c:if>
                </div>
            </c:when>
        </c:choose>



        <script>
            let lastScrollTop = 0;
            const nav = document.querySelector('nav');
            window.addEventListener('scroll', function () {
                let scrollTop = window.pageYOffset || document.documentElement.scrollTop;
                nav.style.top = scrollTop > lastScrollTop ? "-80px" : "0";
                lastScrollTop = scrollTop;
            });
        </script>
    </body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh Sách Voucher</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            /* Thêm phông chữ từ Google Fonts */
            @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

            body {
                font-family: 'Roboto', sans-serif;
                margin: 20px;
                background-color: #f4f1ec; /* Nền màu sáng, giống màu của cà phê */
            }

            nav {
                background-color: #3e2723; /* Màu nâu đậm cho navigation */
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
                color: #6d4c41; /* Màu nâu cho tiêu đề Voucher List */
                text-align: center;
                font-size: 2.5em; /* Tăng kích thước chữ cho tiêu đề */
                margin: 40px 0; /* Khoảng cách trên dưới cho tiêu đề */
                text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1); /* Thêm hiệu ứng bóng cho tiêu đề */
            }

            .table {
                background-color: white; /* Nền trắng cho bảng */
                border-radius: 5px; /* Góc bo tròn */
                overflow: hidden; /* Để bo tròn các góc */
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Bóng cho bảng */
            }

            .table th {
                background-color: #6d4c41; /* Nền cho tiêu đề bảng màu nâu */
                color: white; /* Chữ màu trắng */
            }

            .button {
                background-color: #c69c6d; /* Màu nâu sáng cho các nút */
                color: white; /* Chữ màu trắng */
                font-size: 1.2em;
                padding: 10px 20px; /* Thay đổi padding để nút lớn hơn */
                margin: 10px;
                border: 1px solid #8e7d5b; /* Đường viền màu nâu sáng */
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s; /* Thêm hiệu ứng chuyển màu */
                margin-bottom: 20px; /* Cách phía dưới 20px */
            }

            .button:hover {
                background-color: #a67853; /* Màu nâu đậm hơn khi hover */
            }

            .button-container {
                display: flex;
                justify-content: space-between; /* Đặt khoảng cách giữa các nút */
                margin-top: 10px;
            }

            .btn {
                transition: transform 0.2s; /* Thêm hiệu ứng khi nhấn nút */
            }

            .btn:hover {
                transform: scale(1.05); /* Tăng kích thước khi hover */
            }

            .text-center {
                margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
            }

            /* Hiệu ứng hover cho hàng trong bảng */
            .table tbody tr:hover {
                background-color: #f1e1c1; /* Nền sáng hơn khi hover vào hàng */
            }
        </style>
    </head>
    <body>
        <nav>
            <span class="logo">Voucher Management</span> <!-- Chỉ hiển thị văn bản, không phải là nút -->
            <ul>
                <li><a href="${pageContext.request.contextPath}/trangchu">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
            </ul>
        </nav>

        <h1 class="text-center">Voucher List</h1>

        <div class="button-container">
            <a href="${pageContext.request.contextPath}/Quanly/voucher/CreateVoucher" class="button">Create new voucher</a>
            <a href="${pageContext.request.contextPath}/trangchu" class="button">Back</a>
        </div>

        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>Voucher Name</th>
                    <th>Discount (%)</th>
                    <th>Expiration date</th>
                    <th>Quantity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty vouchers}">
                        <c:forEach var="voucher" items="${vouchers}">
                            <tr>
                                <td>${voucher.id}</td>
                                <td>${voucher.name}</td>
                                <td>${voucher.giamGia}</td>
                                <td>${voucher.ngayHetHan}</td>
                                <td>${voucher.soLuong}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/Quanly/voucher/EditVoucher?id=${voucher.id}" class="button">Edit</a>
                                    <a href="${pageContext.request.contextPath}/Quanly/voucher/DeleteVoucher?id=${voucher.id}" class="button">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="text-center">Không có voucher nào.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <c:choose>
            <c:when test="${totalPages > 1}">
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="${pageContext.request.contextPath}/Quanly/voucher/ListVoucher?page=${currentPage - 1}" class="btn btn-primary">Previous</a>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="${pageContext.request.contextPath}/Quanly/voucher/ListVoucher?page=${i}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="${pageContext.request.contextPath}/Quanly/voucher/ListVoucher?page=${currentPage + 1}" class="btn btn-primary">Next</a>
                    </c:if>
                </div>
            </c:when>
        </c:choose>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

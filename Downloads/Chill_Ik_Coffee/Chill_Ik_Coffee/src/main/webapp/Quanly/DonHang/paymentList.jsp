<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <title>Payment List</title>
        <style>
            /* Basic page styling */
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f1e3;
                color: #4b3832;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }
            h2 {
                text-align: center;
                margin-bottom: 20px;
                font-family: 'Playfair Display', serif;
                font-size: 28px;
                color: #4e342e;
                font-weight: bold;
                margin-bottom: 20px;
            }
            .container {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 90%;
                max-width: 1000px;
            }
            a {
                display: inline-block;
                margin-bottom: 15px;
                padding: 8px 12px;
                background-color: #8d5524;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                font-weight: bold;
            }
            a:hover {
                background-color: #a67c52;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }
            th, td {
                padding: 12px;
                text-align: center;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #8d5524;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #f1d2b2;
            }
            .no-payments {
                text-align: center;
                font-size: 18px;
                color: #4b3832;
            }
            /* Style for the select dropdown */
            .filter-select {
                margin-bottom: 15px;
                padding: 8px;
                border-radius: 5px;
                border: 1px solid #ccc;
                font-size: 14px;
            }
        </style>
        <script>
            function filterPayments() {
                var status = document.getElementById("status").value;
                // Nếu người dùng chọn "All", thì loại bỏ trạng thái lọc khỏi form
                if (status === "All") {
                    document.getElementById("status").removeAttribute("name");
                } else {
                    document.getElementById("status").setAttribute("name", "status");
                }
                document.getElementById("filterForm").submit(); // Gửi form sau khi lựa chọn
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h2>Order List</h2>

            <a href="<c:url value="/trangchu"/>">Back</a> <!-- Optional link -->

            <!-- Filter Select Dropdown with JavaScript onchange event -->
            <form id="filterForm" action="filterPayment" method="get">
                <label for="status">Filter by Status:</label>
                <select id="status" name="status" class="filter-select" onchange="filterPayments()">
                    <option value="">-- Select Status --</option>
                    <option value="Completed">Completed</option>
                    <option value="Pending">Pending</option>
                    <option value="All">All</option> <!-- Thêm lựa chọn All -->
                </select>
            </form>

            <c:if test="${empty paymentList}">
                <p class="no-payments">No payments found.</p>
            </c:if>

            <c:if test="${not empty paymentList}">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>User ID</th>
                        <th>Voucher ID</th>
                        <th>Total Amount</th>
                        <th>Discount Amount</th>
                        <th>Final Amount</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Status</th>
                        
                    </tr>
                    <c:forEach var="payment" items="${paymentList}">
                        <tr>
                            <td>${payment.id}</td>
                            <td>${payment.userId}</td>
                            <td>${payment.voucherId}</td>
                            <td><fmt:formatNumber value="${payment.totalAmount}" type="currency" currencySymbol="" /></td>
                            <td><fmt:formatNumber value="${payment.discountAmount}" type="currency" currencySymbol="" /></td>
                            <td><fmt:formatNumber value="${payment.finalAmount}" type="currency" currencySymbol="" /></td>
                            <td>${payment.address}</td>
                            <td>${payment.phone}</td>
                            <td>${payment.status}</td>
                            
                        </tr>
                    </c:forEach>
                </table>

            </c:if>
            <c:choose>
                <c:when test="${totalPages > 1}">
                    <div class="pagination">
                        <c:if test="${currentPage > 1}">
                            <a href="${pageContext.request.contextPath}/filterPayment?status=${currentStatus}&page=${currentPage - 1}" class="btn btn-primary">Previous</a>
                        </c:if>

                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <a href="${pageContext.request.contextPath}/filterPayment?status=${currentStatus}&page=${i}" class="btn ${i == currentPage ? 'btn-secondary' : 'btn-light'}">${i}</a>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <a href="${pageContext.request.contextPath}/filterPayment?status=${currentStatus}&page=${currentPage + 1}" class="btn btn-primary">Next</a>
                        </c:if>
                    </div>
                </c:when>
            </c:choose>


        </div>
    </body>
</html>

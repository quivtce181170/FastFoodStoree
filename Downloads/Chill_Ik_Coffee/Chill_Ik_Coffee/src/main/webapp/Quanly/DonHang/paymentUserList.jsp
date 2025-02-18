<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- Import JSTL fmt -->
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
                font-size: 36px; /* Increased font size */
                color: #6f4f37; /* Coffee brown color */
                margin-bottom: 20px;
                font-family: 'Lora', serif; /* Applying the Lora font */
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
            
        </style>
    </head>
    <body>
        <div class="container">
            <h2>List of orders placed</h2>

            <a href="<c:url value="/Menu"/>">Back</a> <!-- Optional link -->

            <c:if test="${empty paymentList}">
                <p class="no-payments">No payments found.</p>
            </c:if>

            <c:if test="${not empty paymentList}">
                <table>
                    <tr>
                        
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
        </div>
    </body>
</html>

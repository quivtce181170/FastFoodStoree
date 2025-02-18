<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Your Cart</title>
        <style>
            /* Basic styling for coffee shop theme */
            body {
                font-family: Arial, sans-serif;
                background-color: #f3ece4;
                color: #3e2723;
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                min-height: 100vh;
                margin: 0;
            }
            h1 {
                font-size: 28px;
                color: #5d4037;
                text-align: center;
                margin-bottom: 20px;
            }
            .container {
                width: 90%;
                max-width: 800px;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
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
                background-color: #8d6e63;
                color: #fff;
            }
            tr:nth-child(even) {
                background-color: #f9f4f1;
            }
            tr:hover {
                background-color: #f2dfd3;
            }
            .empty-cart {
                text-align: center;
                font-size: 18px;
                color: #5d4037;
                margin: 20px 0;
            }
            .btn-container {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }
            a.button {
                padding: 10px 20px;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                font-weight: bold;
            }
            .continue-btn {
                background-color: #6d4c41;
            }
            .checkout-btn {
                background-color: #8d6e63;
            }
            a.button:hover {
                opacity: 0.85;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Your Cart</h1>

            <!-- Check if the cart is empty -->
            <c:if test="${empty cartItems}">
                <p class="empty-cart">Your cart is empty. Please add some items to your order.</p>
            </c:if>

            <!-- Display cart items if not empty -->
            <c:if test="${not empty cartItems}">
                <table>
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cartItems}" var="item">
                            <tr>
                                <td>${item.productName}</td>
                                <td>${item.quantity}</td>
                                <td>${item.price}</td>
                                <td>${item.totalPrice}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <!-- Navigation buttons -->
            <div class="btn-container">
                <a href="<c:url value='/Menu'/>" class="button continue-btn">Continue Shopping</a>
                <a href="<c:url value='/checkout'/>" class="button checkout-btn">Proceed to Checkout</a>
            </div>
        </div>
    </body>
</html>

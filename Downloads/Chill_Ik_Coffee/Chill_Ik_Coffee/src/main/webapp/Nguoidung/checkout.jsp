<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Checkout</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f3ece4;
                color: #3e2723;
                display: flex;
                flex-direction: column;
                align-items: center;
                margin: 0;
                padding: 20px;
            }
            h1 {
                color: #5d4037;
                text-align: center;
            }
            table {
                width: 100%;
                max-width: 600px;
                margin-top: 20px;
                border-collapse: collapse;
                text-align: center;
            }
            th, td {
                padding: 12px;
                border: 1px solid #ddd;
            }
            th {
                background-color: #8d6e63;
                color: #fff;
            }
            .container {
                max-width: 600px;
                width: 100%;
            }
            .form-section {
                margin: 20px 0;
            }
            .form-section label, .form-section select, .form-section input {
                width: 100%;
                margin-bottom: 10px;
                padding: 8px;
                font-size: 14px;
            }
            .totals {
                margin-top: 20px;
                font-size: 16px;
            }
            button {
                padding: 10px 20px;
                color: #fff;
                background-color: #8d6e63;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: bold;
            }
            button:hover {
                opacity: 0.9;
            }
            .link {
                text-decoration: none;
                color: #5d4037;
                margin-top: 15px;
                display: inline-block;
            }
        </style>
        <script>
            function updateTotal() {
                const voucherSelect = document.getElementById("voucherId");
                const totalPrice = parseFloat('${totalPrice}');
                const discounts = [
            <c:forEach var="voucher" items="${vouchers}">
                {id: ${voucher.id}, discount: ${voucher.giamGia}},
            </c:forEach>
                ];

                let selectedDiscount = 0;
                const selectedVoucherId = parseInt(voucherSelect.value);

                discounts.forEach(voucher => {
                    if (voucher.id === selectedVoucherId) {
                        selectedDiscount = voucher.discount;
                    }
                });

                const discountAmount = totalPrice * (selectedDiscount / 100);
                const totalAfterDiscount = totalPrice - discountAmount;

                document.getElementById("discountAmount").innerText = discountAmount.toFixed(2) + " VND";
                document.getElementById("finalAmount").innerText = totalAfterDiscount.toFixed(2) + " VND";
                document.getElementById("hiddenDiscountAmount").value = discountAmount.toFixed(2);
                document.getElementById("hiddenFinalAmount").value = totalAfterDiscount.toFixed(2);
            }

            window.onload = function () {
                updateTotal();
            };
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Checkout</h1>

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
                                <td>${item.price} VND</td>
                                <td>${item.totalPrice} VND</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <form action="${pageContext.request.contextPath}/checkout" method="post">
                    <div class="form-section">
                        <label for="voucherId">Select Voucher:</label>
                        <select name="voucherId" id="voucherId" onchange="updateTotal()">
                            <option value="0">Don't Use Voucher</option>
                            <c:forEach items="${vouchers}" var="voucher">
                                <option value="${voucher.id}">
                                    ${voucher.name} - ${voucher.giamGia}% Off
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-section">
                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address" required />
                    </div>

                    <div class="form-section">
                        <label for="phone">Phone Number:</label>
                        <input type="text" id="phone" name="phone" required />
                    </div>

                    <div class="totals">
                        <p>Subtotal: ${totalPrice} VND</p>
                        <p>Discount: <span id="discountAmount">0 VND</span></p>
                        <p>Total After Discount: <span id="finalAmount">${totalPrice} VND</span></p>
                    </div>

                    <input type="hidden" name="totalAmount" value="${totalPrice}" />
                    <input type="hidden" name="discountAmount" id="hiddenDiscountAmount" value="0" />
                    <input type="hidden" name="finalAmount" id="hiddenFinalAmount" value="${totalPrice}" />

                    <button type="submit" name="action" value="confirmPayment">Confirm Payment</button>
                </form>
            </c:if>

            <a href="<c:url value='/Menu'/>" class="link">Continue Shopping</a>
        </div>
    </body>
</html>

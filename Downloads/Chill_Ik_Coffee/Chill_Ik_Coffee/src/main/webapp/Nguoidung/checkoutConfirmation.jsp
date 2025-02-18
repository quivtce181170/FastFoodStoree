<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Payment Confirmation</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f3ece4;
                color: #3e2723;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
                margin: 0;
                text-align: center;
            }
            h1 {
                color: #5d4037;
                font-size: 24px;
                margin-bottom: 10px;
            }
            p {
                font-size: 16px;
                margin-bottom: 20px;
            }
            a {
                text-decoration: none;
                color: #fff;
                background-color: #8d6e63;
                padding: 10px 20px;
                border-radius: 5px;
                font-weight: bold;
            }
            a:hover {
                background-color: #704c40;
            }
        </style>
    </head>
    <body>
        <img src="https://cf.shopee.vn/file/be19aff36937c99fd1d40c5cecdca36a" alt="Voucher" width="10%">
        <h1>Payment Successfully Confirmed!</h1>
        <p>Thank you for your purchase.</p>
        <a href="<c:url value='/Menu'/>">Return to Menu</a>
    </body>
</html>
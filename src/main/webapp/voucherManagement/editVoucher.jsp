<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Voucher</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f4f7fa;
                margin: 0;
                padding: 0;
            }

            .container {
                width: 50%;
                margin: 2rem auto;
                background-color: white;
                padding: 2rem;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            h2 {
                text-align: center;
                margin-bottom: 1rem;
            }

            label {
                font-weight: 500;
                margin-bottom: 0.5rem;
                display: block;
            }

            input, select {
                width: 100%;
                padding: 0.75rem;
                margin-bottom: 1rem;
                border-radius: 8px;
                border: 1px solid #ccc;
                font-size: 1rem;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                cursor: pointer;
                border: none;
                transition: background-color 0.3s;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            .back-link {
                display: block;
                text-align: center;
                margin-top: 1rem;
            }

            .back-button {
                background-color: #4CAF50; /* Green background */
                color: white; /* White text */
                border: none;
                padding: 0.5rem 1rem;
                font-size: 1rem;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .back-button:hover {
                background-color: #0056b3; /* Darker blue on hover */
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h2>Edit Voucher</h2>
            <form action="/voucherManagement/editVoucher" method="post">
                <input type="hidden" name="voucherId" value="${voucher.id}">

                <label for="name">Voucher Name:</label>
                <input type="text" name="name" value="${voucher.name}" required>

                <label for="discount">Discount Percentage:</label>
                <input type="number" name="discount" value="${voucher.discountPercentage}" step="0.01" min="0" required> 

                <label for="quantity">Quantity:</label>
                <input type="number" name="soLuong" value="${voucher.soLuong}" min="1" required>

                <label for="validFrom">Valid From:</label>
                <input type="date" name="validFrom" value="${voucher.validFrom}" required>

                <label for="validUntil">Valid Until:</label>
                <input type="date" name="validUntil" value="${voucher.validUntil}" required>

                <label for="status">Status:</label>
                <select name="status">
                    <option value="Active" ${voucher.status == 'Active' ? 'selected' : ''}>Active</option>
                    <option value="Out of Stock" ${voucher.status == 'Out of Stock' ? 'selected' : ''}>Out of Stock</option>
                </select>

                <input type="submit" value="Update Voucher">
            </form>

            <div class="back-link">
                <button type="button" class="back-button" onclick="window.location.href = '<%= request.getContextPath()%>/voucherManagement/viewVoucher'" id="back">Back</button>
            </div>
        </div>
    </body>
</html>

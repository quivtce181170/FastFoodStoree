<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.Voucher" %>
<%
    List<Voucher> vouchers = (List<Voucher>) request.getAttribute("vouchers");
    if (vouchers == null) {
        out.println("<p class='text-danger'>Error: No data received from the server.</p>");
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Voucher List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <head>
        <title>Voucher Management</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 30px;
            }
            table {
                margin-top: 20px;
            }
            th {
                background-color: #343a40;
                color: white;
                text-align: center;
            }
            .btn-edit {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 5px 10px;
                border-radius: 5px;
            }
            .btn-edit:hover {
                background-color: #218838;
            }

            /* Yes button */
            .btn-yes {
                background-color: #d9534f;
                color: white;
            }

            .btn-yes:hover {
                background-color: #c9302c;
            }

            /* No button */
            .btn-no {
                background-color: #5bc0de;
                color: white;
            }

            .btn-no:hover {
                background-color: #31b0d5;
            }/* Buttons */
            .modal-buttons {
                margin-top: 20px;
            }

            .modal button {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                margin: 5px;
                transition: 0.3s;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="text-center text-primary">List of Voucher</h2>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Discount (%)</th>
                        <th>Quantity</th>
                        <th>Valid From</th>
                        <th>Valid Until</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (vouchers != null && !vouchers.isEmpty()) {
                            for (Voucher v : vouchers) {%>
                    <tr>
                        <td><%= v.getName()%></td>
                        <td><%= v.getDiscountPercentage()%></td>
                        <td><%= v.getSoLuong()%></td>
                        <td><%= v.getValidFrom()%></td>
                        <td><%= v.getValidUntil()%></td>
                        <td><%= v.getStatus()%></td>
                    </tr>
                    <% }
                    } else { %>
                    <tr>
                        <td colspan="10" class="text-center text-danger">No vouchers found.</td>
                    </tr>
                    <% }%>



                </tbody>
            </table>
        </div>
    </body>
</html>
<!--http://localhost:8080/FastFoodStore2/Customer/viewVoucherCusServlet-->
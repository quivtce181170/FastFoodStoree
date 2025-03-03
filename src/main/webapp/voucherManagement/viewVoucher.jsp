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
            <h2 class="text-center text-primary">Voucher Management</h2>

            <div class="d-flex justify-content-between my-3">
                <a href="addVoucher.jsp" class="btn btn-success">Create New Voucher</a>
            </div>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Discount (%)</th>
                        <th>Quantity</th>
                        <th>Valid From</th>
                        <th>Valid Until</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Modal Xác nhận Xóa -->
                <div id="deleteModal" class="modal" style="display: none; position: fixed; z-index: 1000; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5);">
                    <div class="modal-content" style="background-color: white; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 30%; text-align: center; border-radius: 10px;">
                        <h3>Are you sure you want to delete this voucher?</h3>
                        <input type="hidden" id="voucherIdToDelete">
                        <button onclick="confirmDelete()" style="background-color: red; color: white; padding: 10px; border: none; cursor: pointer; margin-right: 10px;">Yes</button>
                        <button onclick="closeModal()" style="background-color: gray; color: white; padding: 10px; border: none; cursor: pointer;">No</button>
                    </div>
                </div>

                <% if (vouchers != null && !vouchers.isEmpty()) {
                        for (Voucher v : vouchers) {%>
                <tr>
                    <td><%= v.getId()%></td>
                    <td><%= v.getName()%></td>
                    <td><%= v.getDiscountPercentage()%></td>
                    <td><%= v.getSoLuong()%></td>
                    <td><%= v.getValidFrom()%></td>
                    <td><%= v.getValidUntil()%></td>
                    <td><%= v.getStatus()%></td>
                    <td><%= v.getCreatedAt()%></td>
                    <td><%= v.getUpdatedAt()%></td>
                    <td>
                        <a href="editVoucher.jsp?id=<%= v.getId()%>" class="btn-edit">Edit</a>
                        <button onclick="showDeleteModal(<%= v.getId()%>)" style="background-color: red; color: white; padding: 5px; border: none; cursor: pointer;">Delete</button>
                    </td>

                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="10" class="text-center text-danger">No vouchers found.</td>
                </tr>
                <% }%>
                <script>
                    function showDeleteModal(voucherId) {
                        document.getElementById("voucherIdToDelete").value = voucherId;
                        document.getElementById("deleteModal").style.display = "block";
                    }

                    function closeModal() {
                        document.getElementById("deleteModal").style.display = "none";
                    }

                    function confirmDelete() {
                        let voucherId = document.getElementById("voucherIdToDelete").value;

                        fetch('<%= request.getContextPath()%>/voucherManagement/deleteVoucher', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded'
                            },
                            body: `id=${voucherId}`
                        })
                                .then(response => {
                                    if (response.ok) {
                                        window.location.reload(); // Refresh the page to update voucher list
                                    } else {
                                        alert("Failed to delete voucher.");
                                    }
                                })
                                .catch(error => console.error('Error:', error));

                        closeModal();
                    }
                </script>


                </tbody>
            </table>
        </div>
    </body>
</html>
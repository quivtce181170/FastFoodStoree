<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create New Voucher</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>.btn-primary {

            </style>
        </head>
        <body class="bg-light">
            <div class="container mt-5">
                <div class="card shadow-lg p-4">
                    <h2 class="text-center text-primary">Create New Voucher</h2>

                    <form action="<%= request.getContextPath()%>/voucherManagement/addVoucher" method="post">
                        <!-- Voucher Name -->
                        <div class="mb-3">
                            <label for="name" class="form-label fw-bold">Voucher Name</label>
                            <input type="text" id="name" name="name" class="form-control" placeholder="Enter voucher name" required>
                        </div>

                        <!-- Discount Percentage -->
                        <div class="mb-3">
                            <label for="discount" class="form-label fw-bold">Discount (%)</label>
                            <input type="number" step="0.01" id="discount" name="discount" class="form-control" placeholder="Enter discount percentage" required>
                        </div>

                        <!-- Quantity -->
                        <div class="mb-3">
                            <label for="soLuong" class="form-label fw-bold">Quantity</label>
                            <input type="number" id="soLuong" name="soLuong" class="form-control" placeholder="Enter quantity (1-99)" required>
                        </div>

                        <!-- Start Date (Fixed Issue) -->
                        <div class="mb-3">
                            <label for="validFrom" class="form-label fw-bold">Start Date</label>
                            <input type="date" id="validFrom" name="validFrom" class="form-control" required>
                        </div>

                        <!-- Expiry Date -->
                        <div class="mb-3">
                            <label for="validUntil" class="form-label fw-bold">Expiry Date</label>
                            <input type="date" id="validUntil" name="validUntil" class="form-control" required>
                        </div>

                        <!-- Status -->
                        <div class="mb-3">
                            <label for="status" class="form-label fw-bold">Status</label>
                            <select id="status" name="status" class="form-select">
                                <option value="Active">Active</option>
                                <option value="Inactive">Inactive</option>
                            </select>
                        </div>

                        <!-- Buttons -->
                        <div class="d-flex justify-content-between">
                            <button type="button" class="btn btn-primary" onclick="window.location.href = '<%= request.getContextPath()%>/voucherManagement/viewVoucher'">Back</button>
                            <button type="submit" class="btn btn-primary">Create</button>
                        </div>
                    </form>

                </div>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>
    </html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.DoanhThu" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Doanh thu</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f7f7f7;
                color: #333;
            }

            .header-title {
                color: #2e7d32;
                text-align: center;
                font-size: 2.5rem;
                margin-bottom: 40px;
            }

            #back {
                background-color: #b2ebf2;
                color: #00796b;
                border: 1px solid #00796b;
                padding: 10px 20px;
                border-radius: 5px;
                font-weight: bold;
                float: right;
                margin-bottom: 20px;
                transition: background-color 0.3s ease;
            }

            #back:hover {
                background-color: #00796b;
                color: #ffffff;
            }

            h2 {
                margin-bottom: 20px;
                color: #00796b;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 40px;
                background-color: #ffffff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 12px 15px;
                text-align: left;
                border-bottom: 1px solid #e0e0e0;
            }

            th {
                background-color: #e0f7fa;
                font-weight: bold;
            }

            tr:hover {
                background-color: #f1f8e9;
            }
            #exportToExcel{
                display: flex;
                text-align: left;
                margin: 20px 0;
            }
        </style>

        <!-- SheetJS (XLSX) for Excel Export -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.full.min.js"></script>

    </head>
    <body>
        <div class="container mt-4">
            <h1 class="header-title">Item Revenue</h1>

            <button type="button" onclick="window.location.href = '<%= request.getContextPath() %>/trangchu'" id="back">Back</button>

            <!-- Nút Export to Excel -->
            <div class="text-right mt-4">
                <button type="button" id="exportToExcel" class="btn btn-success">Export to Excel</button>
            </div>

            <!-- Tổng doanh thu theo ngày -->
            <h2>Total Daily Revenue</h2>
            <table class="table table-striped" id="tableDailyRevenue">
                <thead>
                    <tr>
                        <th>Day</th>
                        <th>Total Revenue</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="tongDoanhThuTheoNgay" value="${requestScope.tongDoanhThuTheoNgay}" />
                    <c:forEach var="dt" items="${tongDoanhThuTheoNgay}">
                        <tr>
                            <td>${dt.thoiGian}</td>
                            <td>${dt.doanhThu}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Tổng doanh thu theo tuần -->
            <h2>Total Weekly Revenue</h2>
            <table class="table table-striped" id="tableWeeklyRevenue">
                <thead>
                    <tr>
                        <th>Week</th>
                        <th>Total Revenue</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="tongDoanhThuTheoTuan" value="${requestScope.tongDoanhThuTheoTuan}" />
                    <c:forEach var="dt" items="${tongDoanhThuTheoTuan}">
                        <tr>
                            <td>${dt.thoiGian}</td>
                            <td>${dt.doanhThu}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Tổng doanh thu theo tháng -->
            <h2>Total Monthly Revenue</h2>
            <table class="table table-striped" id="tableMonthlyRevenue">
                <thead>
                    <tr>
                        <th>Month</th>
                        <th>Total Revenue</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="tongDoanhThuTheoThang" value="${requestScope.tongDoanhThuTheoThang}" />
                    <c:forEach var="dt" items="${tongDoanhThuTheoThang}">
                        <tr>
                            <td>${dt.thoiGian}</td>
                            <td>${dt.doanhThu}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Tổng doanh thu theo năm -->
            <h2>Total Yearly Revenue</h2>
            <table class="table table-striped" id="tableYearlyRevenue">
                <thead>
                    <tr>
                        <th>Year</th>
                        <th>Total Revenue</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="tongDoanhThuTheoNam" value="${requestScope.tongDoanhThuTheoNam}" />
                    <c:forEach var="dt" items="${tongDoanhThuTheoNam}">
                        <tr>
                            <td>${dt.thoiGian}</td>
                            <td>${dt.doanhThu}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="text-right mt-4">
                <button type="button" onclick="window.location.href = '<%= request.getContextPath() %>/trangchu'" id="back">Back</button>
            </div>
        </div>

        <script>
            // Hàm định dạng số thành tiền VND
            function formatCurrency(value) {
                return value.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            }

            // Định dạng các cột doanh thu sau khi tải trang
            document.addEventListener("DOMContentLoaded", function () {
                // Định dạng doanh thu theo ngày
                document.querySelectorAll("#tableDailyRevenue tbody td:nth-child(2)").forEach(cell => {
                    cell.textContent = formatCurrency(parseFloat(cell.textContent));
                });

                // Định dạng doanh thu theo tuần
                document.querySelectorAll("#tableWeeklyRevenue tbody td:nth-child(2)").forEach(cell => {
                    cell.textContent = formatCurrency(parseFloat(cell.textContent));
                });

                // Định dạng doanh thu theo tháng
                document.querySelectorAll("#tableMonthlyRevenue tbody td:nth-child(2)").forEach(cell => {
                    cell.textContent = formatCurrency(parseFloat(cell.textContent));
                });

                // Định dạng doanh thu theo năm
                document.querySelectorAll("#tableYearlyRevenue tbody td:nth-child(2)").forEach(cell => {
                    cell.textContent = formatCurrency(parseFloat(cell.textContent));
                });
            });

            // Sự kiện xuất ra Excel
            document.getElementById('exportToExcel').addEventListener('click', function () {
                var wb = XLSX.utils.book_new();

                // Chuyển dữ liệu từ bảng Doanh thu theo ngày
                var tableDoanhThuNgay = document.getElementById('tableDailyRevenue');
                var wsDoanhThuNgay = XLSX.utils.table_to_sheet(tableDoanhThuNgay);
                XLSX.utils.book_append_sheet(wb, wsDoanhThuNgay, 'Doanh Thu Theo Ngay');

                // Chuyển dữ liệu từ bảng Doanh thu theo tuần
                var tableDoanhThuTuan = document.getElementById('tableWeeklyRevenue');
                var wsDoanhThuTuan = XLSX.utils.table_to_sheet(tableDoanhThuTuan);
                XLSX.utils.book_append_sheet(wb, wsDoanhThuTuan, 'Doanh Thu Theo Tuan');

                // Chuyển dữ liệu từ bảng Doanh thu theo tháng
                var tableDoanhThuThang = document.getElementById('tableMonthlyRevenue');
                var wsDoanhThuThang = XLSX.utils.table_to_sheet(tableDoanhThuThang);
                XLSX.utils.book_append_sheet(wb, wsDoanhThuThang, 'Doanh Thu Theo Thang');

                // Chuyển dữ liệu từ bảng Doanh thu theo năm
                var tableDoanhThuNam = document.getElementById('tableYearlyRevenue');
                var wsDoanhThuNam = XLSX.utils.table_to_sheet(tableDoanhThuNam);
                XLSX.utils.book_append_sheet(wb, wsDoanhThuNam, 'Doanh Thu Theo Nam');

                // Xuất file Excel
                XLSX.writeFile(wb, 'DoanhThu.xlsx');
            });
        </script>


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
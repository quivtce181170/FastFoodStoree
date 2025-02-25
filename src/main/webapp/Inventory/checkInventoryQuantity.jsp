<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh Sách Kho Hàng</title>
    </head>
    <body>

        <h1>Danh Sách Kho Hàng Sắp Xếp Theo Số Lượng</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Mã Kho</th>
                    <th>Tên Món Ăn</th>
                    <th>Số Lượng</th>
                    <th>Nhà Cung Cấp</th>
                    <th>Thông Tin Liên Hệ</th>
                    <th>Giá Nhập</th>
                    <th>Giá Bán</th>
                    <th>Ngày Tạo</th>
                    <th>Hành Động</th> <!-- Cột Hành Động -->
                </tr>
            </thead>
            <tbody>
                <!-- Lặp qua danh sách kho hàng từ servlet -->
                <c:forEach var="inventory" items="${inventoryList}">
                    <tr>
                        <td>${inventory.inventoryId}</td>
                        <td>${inventory.dishName}</td>
                        <td>${inventory.quantity}</td>
                        <td>${inventory.supplierName}</td>
                        <td>${inventory.contactInfo}</td>
                        <td>${inventory.purchasePrice}</td>
                        <td>${inventory.sellingPrice}</td>
                        <td>${inventory.createdAt}</td>
                        <td>
                            <!-- Các nút hành động -->
                            <form action="${pageContext.request.contextPath}/Inventory/addStockToInventory" method="get">
                                <input type="hidden" name="inventoryId" value="${inventory.inventoryId}">
                                <input type="submit" value="Thêm Hàng Vào Kho">
                            </form>

                            <form action="${pageContext.request.contextPath}/Inventory/updateInventoryQuantity" method="get">
                                <input type="hidden" name="inventoryId" value="${inventory.inventoryId}">
                                <input type="submit" value="Cập Nhật Số Lượng Kho">
                            </form>

                            <form action="${pageContext.request.contextPath}/Inventory/removeExpiredIngredients" method="get">
                                <input type="hidden" name="inventoryId" value="${inventory.inventoryId}">
                                <input type="submit" value="Xóa Nguyên Liệu Hết Hạn">
                            </form>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>

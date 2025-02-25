<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Hàng Vào Kho</title>
    <link rel="stylesheet" href="/path/to/your/css/styles.css"> <!-- Nếu cần thêm style CSS -->
</head>
<body>
    <div class="container">
        <h2>Thêm Hàng Vào Kho</h2>
        
        <!-- Hiển thị thông báo nếu có -->
        <c:if test="${not empty message}">
            <div class="alert">
                <c:out value="${message}"/>
            </div>
        </c:if>

        <!-- Form để thêm kho hàng -->
      <form action="/FastFoodStore/Inventory/addStocktoInventory" method="post">

            <div class="form-group">
                <label for="dish_name">Tên Món Ăn:</label>
                <input type="text" id="dish_name" name="dish_name" required="required" />
            </div>

            <div class="form-group">
                <label for="supplier_name">Tên Nhà Cung Cấp:</label>
                <input type="text" id="supplier_name" name="supplier_name" required="required" />
            </div>

            <div class="form-group">
                <label for="contact_info">Thông Tin Liên Hệ:</label>
                <input type="text" id="contact_info" name="contact_info" required="required" />
            </div>

            <div class="form-group">
                <label for="quantity">Số Lượng:</label>
                <input type="number" id="quantity" name="quantity" required="required" />
            </div>

            <div class="form-group">
                <label for="purchase_price">Giá Nhập:</label>
                <input type="number" id="purchase_price" name="purchase_price" step="0.01" required="required" />
            </div>

            <div class="form-group">
                <label for="selling_price">Giá Bán:</label>
                <input type="number" id="selling_price" name="selling_price" step="0.01" required="required" />
            </div>

            <div class="form-group">
                <button type="submit">Thêm Hàng</button>
            </div>
        </form>
    </div>
</body>
</html>

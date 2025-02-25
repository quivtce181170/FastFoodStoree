package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Inventory;
import db.DBContext;

public class InventoryDAO extends DBContext {

    // Phương thức lấy danh sách kho hàng và sắp xếp theo số lượng tồn kho tăng dần
    public List<Inventory> getInventorySortedByQuantity() {
        List<Inventory> inventories = new ArrayList<>();
        String query = "SELECT i.inventory_id, "
                + "d.dish_name, "
                + "i.quantity, "
                + "i.supplier_name, "
                + "i.contact_info, "
                + "i.purchase_price, "
                + "i.selling_price, "
                + "i.created_at "
                + "FROM Inventory i "
                + "JOIN Dishes d ON i.dish_id = d.dish_id "
                + "ORDER BY i.quantity ASC";  // Sắp xếp theo số lượng tồn kho tăng dần

        try ( Connection conn = getConnection(); // Kết nối cơ sở dữ liệu
                  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            // Đọc kết quả từ ResultSet và thêm vào danh sách inventories
            while (rs.next()) {
                inventories.add(new Inventory(
                        rs.getInt("inventory_id"),
                        rs.getString("dish_name"),
                        rs.getInt("quantity"),
                        rs.getString("supplier_name"),
                        rs.getString("contact_info"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("selling_price"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách kho hàng: " + e.getMessage());
        }
        return inventories;
    }

   public boolean insertInventory(String dishName, String supplierName, String contactInfo, 
                                int quantity, double purchasePrice, double sellingPrice) {
    // Bước 1: Lấy dish_id từ dishName
    int dishId = getDishIdByName(dishName);
    if (dishId == -1) {
        return false; // Món ăn không tồn tại
    }

    // Bước 2: Chèn vào bảng Inventory
    String query = "INSERT INTO Inventory (dish_id, supplier_name, contact_info, quantity, purchase_price, selling_price, created_at) "
                 + "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";

    try (Connection conn = getConnection();  
         PreparedStatement ps = conn.prepareStatement(query)) {

        // Gán giá trị vào PreparedStatement
        ps.setInt(1, dishId);  // Gán dish_id
        ps.setString(2, supplierName);  // Gán tên nhà cung cấp
        ps.setString(3, contactInfo);   // Gán thông tin liên hệ
        ps.setInt(4, quantity);  // Gán số lượng
        ps.setDouble(5, purchasePrice);  // Gán giá nhập
        ps.setDouble(6, sellingPrice);   // Gán giá bán

        // Thực hiện chèn dữ liệu
        int rowsInserted = ps.executeUpdate();
        return rowsInserted > 0; // Trả về true nếu chèn thành công
    } catch (SQLException e) {
        System.out.println("Lỗi khi chèn kho hàng: " + e.getMessage());
        return false; // Nếu có lỗi xảy ra, trả về false
    }
}

// Phương thức hỗ trợ lấy dish_id từ tên món ăn
private int getDishIdByName(String dishName) {
    String query = "SELECT dish_id FROM Dishes WHERE dish_name = ?";
    try (Connection conn = getConnection();  
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, dishName); // Gán tên món ăn vào PreparedStatement

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("dish_id");  // Trả về dish_id nếu tìm thấy
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi khi tìm kiếm dish_id: " + e.getMessage());
    }
    return -1; // Trả về -1 nếu không tìm thấy món ăn
}

}

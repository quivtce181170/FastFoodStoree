package DAO;

import db.DBContext;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBContext {

    public boolean createOrder(Order order) {
        String query = "INSERT INTO Orders (user_id, total_amount, payment_method, delivery_address, estimated_delivery_date, status, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, 'Pending', GETDATE(), GETDATE())";
        try {
            return execQuery(query, new Object[]{
                order.getUserId(), order.getTotalAmount(), order.getPaymentMethod(),
                order.getDeliveryAddress(), order.getEstimatedDeliveryDate()
            }) > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi tạo đơn hàng: " + e.getMessage());
            return false;
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try (ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("user_id"),
                        rs.getBigDecimal("total_amount"),
                        rs.getString("payment_method"),
                        rs.getString("delivery_address"),
                        rs.getDate("estimated_delivery_date")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách đơn hàng: " + e.getMessage());
        }
        return orders;
    }
}

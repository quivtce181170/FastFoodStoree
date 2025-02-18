package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;

public class CartDAO {

    private Connection connection;

    public CartDAO(Connection connection) {
        this.connection = connection;
    }

    // Add or update an item in the cart
    public void addToCart(int userId, int drinkId, int quantity) throws SQLException {
        String sql = "INSERT INTO Cart_Items (cart_id, drink_id, quantity) "
                + "VALUES ((SELECT cart_id FROM Carts WHERE user_id = ?), ?, ?) "
                + "ON DUPLICATE KEY UPDATE quantity = quantity + ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, drinkId);
            stmt.setInt(3, quantity);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        }
    }

    // Get cart items for a specific user
    public List<CartItem> getCartItems(int userId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT di.name, ci.quantity, di.price FROM Cart_Items ci "
                + "JOIN Carts c ON ci.cart_id = c.cart_id "
                + "JOIN Drinks di ON ci.drink_id = di.drink_id "
                + "WHERE c.user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String productName = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    cartItems.add(new CartItem(productName, quantity, price, price * quantity));
                }
            }
        }
        return cartItems;
    }

    // Calculate total price of items in the cart for a specific user
    public double getTotalPrice(int userId) throws SQLException {
        double totalPrice = 0.0;
        String sql = "SELECT SUM(ci.quantity * di.price) AS total FROM Cart_Items ci "
                + "JOIN Carts c ON ci.cart_id = c.cart_id "
                + "JOIN Drinks di ON ci.drink_id = di.drink_id "
                + "WHERE c.user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalPrice = rs.getDouble("total");
                }
            }
        }
        return totalPrice;
    }

    // Validate the voucher and get discount percentage (you need to implement this)
    public double validateVoucher(String voucherCode) throws SQLException {
        double discount = 0.0;
        String sql = "SELECT discount_percentage FROM Vouchers WHERE code = ? AND is_active = 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, voucherCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    discount = rs.getDouble("discount_percentage");
                }
            }
        }
        return discount;
    }
}

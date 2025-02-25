/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;
import model.Drink;

/**
 *
 * @author Nguyen Ngoc Phat - CE180321
 */
public class DrinkDAO extends DBContext {

    // 1. Read
    public ArrayList<Drink> getAll() {
        ArrayList<Drink> drinks = new ArrayList<>();
        String query = "SELECT id, name, gia, so_luong, loai_nuoc_id, trang_thai, imageUrl FROM mon_nuoc";

        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                drinks.add(new Drink(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("gia"),
                        rs.getInt("so_luong"),
                        rs.getInt("loai_nuoc_id"),
                        rs.getBoolean("trang_thai"),
                        rs.getString("imageUrl")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving drinks");
        }
        return drinks;
    }

    public Drink getOnlyById(int id) {
        String query = "SELECT id, name, gia, so_luong, loai_nuoc_id, trang_thai, imageUrl FROM mon_nuoc WHERE id = ?";
        Object[] params = {id};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                return new Drink(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("gia"),
                        rs.getInt("so_luong"),
                        rs.getInt("loai_nuoc_id"),
                        rs.getBoolean("trang_thai"),
                        rs.getString("imageUrl")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving drink by ID");
        }
        return null;
    }

    public ArrayList<Drink> getOnlyByLoaiNuocId(int loaiNuocId, int page, int pageSize) {
        ArrayList<Drink> drinks = new ArrayList<>();
        String query = "SELECT id, name, gia, so_luong, loai_nuoc_id, trang_thai, imageUrl FROM mon_nuoc WHERE loai_nuoc_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int offset = (page - 1) * pageSize;
        Object[] params = {loaiNuocId, offset, pageSize};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                drinks.add(new Drink(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("gia"),
                        rs.getInt("so_luong"),
                        rs.getInt("loai_nuoc_id"),
                        rs.getBoolean("trang_thai"),
                        rs.getString("imageUrl")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving drinks with pagination");
        }
        return drinks;
    }

    // 2. Create
    public int create(Drink drink) {
        String getMaxIdQuery = "SELECT MAX(id) AS maxId FROM mon_nuoc";
        try ( ResultSet rs = execSelectQuery(getMaxIdQuery)) {
            int nextId = 1; // Start with ID 1
            if (rs.next()) {
                Integer maxId = rs.getInt("maxId");
                if (maxId != null) {
                    nextId = maxId + 1; // Increment if there are existing records
                }
            }

            String createMovieQuery = "INSERT INTO mon_nuoc (id, name, gia, so_luong, loai_nuoc_id, trang_thai, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {
                nextId,
                drink.getName(),
                drink.getGia(),
                drink.getSoluong(),
                drink.getLoainuocid(),
                drink.isTrangthai(),
                drink.getImageUrl()
            };
            return execQuery(createMovieQuery, params);
        } catch (SQLException ex) {
            System.out.println("Error creating drink: " + ex.getMessage());
        }
        return 0;
    }

    // 3. Update
    public int update(Drink drink) {
        String sql = "UPDATE mon_nuoc SET name = ?, gia = ?, so_luong = ?, loai_nuoc_id = ?, trang_thai = ?, imageUrl = ? WHERE id = ?";
        Object[] params = {
            drink.getName(),
            drink.getGia(),
            drink.getSoluong(),
            drink.getLoainuocid(),
            drink.isTrangthai(),
            drink.getImageUrl(),
            drink.getId()
        };
        try {
            return execQuery(sql, params);
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    // 4. Delete
    public int delete(int id) {
        String deleteSql = "DELETE FROM mon_nuoc WHERE id = ?";
        String renumberSql = "WITH CTE AS ("
                + "SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS new_id "
                + "FROM mon_nuoc "
                + ") "
                + "UPDATE mon_nuoc "
                + "SET id = CTE.new_id "
                + "FROM CTE "
                + "WHERE mon_nuoc.id = CTE.id;";

        Connection conn = null;

        try {
            // Get the connection from DBContext
            conn = getConnection();
            conn.setAutoCommit(false); // Disable auto-commit

            // Delete the movie
            Object[] params = {id};
            int result = execQuery(deleteSql, params);

            if (result > 0) {
                // Only renumber IDs if the deletion was successful
                execQuery(renumberSql, null);
            }

            // Commit the transaction
            conn.commit();
            return result;
        } catch (SQLException ex) {
            System.out.println("Error during delete operation: " + ex.getMessage());
            if (conn != null) {
                try {
                    // Roll back the transaction in case of error
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            return 0;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Reset auto-commit back to true
                }
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
    // Method to calculate the total price of the items in the cart

    public double calculateTotalPrice(Map<Integer, Integer> cart) {
        double totalPrice = 0.0;

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int drinkId = entry.getKey();
            int quantity = entry.getValue();
            Drink drink = getOnlyById(drinkId); // Fetch the drink by ID

            if (drink != null) {
                totalPrice += drink.getGia() * quantity; // Add price for the quantity
            }
        }
        return totalPrice;
    }

// Method to get cart items based on a map of IDs and quantities
    public List<CartItem> getCartItems(Map<Integer, Integer> cart) {
        List<CartItem> cartItems = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int drinkId = entry.getKey();
            int quantity = entry.getValue();
            Drink drink = getOnlyById(drinkId);

            if (drink != null) {
                cartItems.add(new CartItem(drink.getName(), quantity, drink.getGia(), quantity * drink.getGia()));
            }
        }
        return cartItems;
    }

    public ArrayList<Drink> getDrinksByPage(int page, int pageSize) {
        ArrayList<Drink> drinks = new ArrayList<>();
        String query = "SELECT id, name, gia, so_luong, loai_nuoc_id, trang_thai, imageUrl "
                + "FROM mon_nuoc "
                + "ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int offset = (page - 1) * pageSize;
        Object[] params = {offset, pageSize};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                drinks.add(new Drink(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("gia"),
                        rs.getInt("so_luong"),
                        rs.getInt("loai_nuoc_id"),
                        rs.getBoolean("trang_thai"),
                        rs.getString("imageUrl")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving drinks with pagination");
        }
        return drinks;
    }

    public int getTotalDrinkCount() {
        String query = "SELECT COUNT(*) AS total FROM mon_nuoc";
        try ( ResultSet rs = execSelectQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving total drink count");
        }
        return 0;
    }

}

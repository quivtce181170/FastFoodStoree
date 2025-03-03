/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Voucher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoucherDAO {

    private final DBContext dbContext;

    public VoucherDAO() {
        this.dbContext = new DBContext();
    }

    public List<Voucher> getAllVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String sql = "SELECT * FROM Vouchers";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                vouchers.add(new Voucher(
                        rs.getInt("voucher_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("discount_percentage"),
                        rs.getInt("so_luong"),
                        rs.getDate("valid_from"),
                        rs.getDate("valid_until"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                ));
            }
        } catch (Exception e) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error retrieving vouchers");
            System.out.println("Fetching vouchers from DB...");
            System.out.println("Total vouchers retrieved: " + vouchers.size());

        }
        return vouchers;
    }

    public void addVoucher(Voucher voucher) {
        String sql = "INSERT INTO Vouchers ( name, discount_percentage, so_luong, valid_from, valid_until, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, voucher.getName());
            ps.setBigDecimal(2, voucher.getDiscountPercentage());
            ps.setInt(3, voucher.getSoLuong());
            ps.setDate(4, new java.sql.Date(voucher.getValidFrom().getTime()));
            ps.setDate(5, new java.sql.Date(voucher.getValidUntil().getTime()));
            ps.setString(6, voucher.getStatus());
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            System.out.println("✅ Voucher added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditVoucher(Voucher voucher) {
        String sql = "UPDATE Vouchers SET name=?, discount_percentage=?,  so_luong=?, valid_from=?, valid_until=?,status=?, updated_at=GETDATE() WHERE voucher_id=?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, voucher.getName());
            ps.setBigDecimal(2, voucher.getDiscountPercentage());
            ps.setInt(3, voucher.getSoLuong());
            ps.setDate(4, new java.sql.Date(voucher.getValidFrom().getTime()));
            ps.setDate(5, new java.sql.Date(voucher.getValidUntil().getTime()));
            ps.setString(6, voucher.getStatus());
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteVoucher(int id) {
        String sql = "DELETE FROM Vouchers WHERE voucher_id = ?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Voucher deleted successfully! ID: " + id);
                return true;  // Return true if deletion was successful
            } else {
                System.out.println("⚠ No voucher found with ID: " + id);
                return false;  // Return false if no row was deleted
            }

        } catch (SQLException e) {
            System.err.println("❌ Error deleting voucher with ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    public Voucher getVoucherById(int id) {
        Voucher voucher = null;
        String sql = "SELECT * FROM Vouchers WHERE voucher_id = ?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    voucher = new Voucher(
                            rs.getInt("voucher_id"),
                            rs.getString("name"),
                            rs.getBigDecimal("discount_percentage"),
                            rs.getInt("so_luong"),
                            rs.getDate("valid_from"),
                            rs.getDate("valid_until"),
                            rs.getString("status"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voucher;
    }

    public boolean updateVoucher(Voucher voucher) {
        String sql = "UPDATE Vouchers SET name=?, discount_percentage=?, so_luong=?, valid_from=?, valid_until=?, status=?, updated_at=GETDATE() WHERE voucher_id=?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, voucher.getName());
            ps.setBigDecimal(2, voucher.getDiscountPercentage());
            ps.setInt(3, voucher.getSoLuong());
            ps.setDate(4, new java.sql.Date(voucher.getValidFrom().getTime()));
            ps.setDate(5, new java.sql.Date(voucher.getValidUntil().getTime()));
            ps.setString(6, voucher.getStatus());
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

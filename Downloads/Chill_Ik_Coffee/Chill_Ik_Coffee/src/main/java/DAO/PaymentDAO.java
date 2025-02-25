package DAO;

import db.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Drink;
import model.Payment;
import model.TaiKhoan;

public class PaymentDAO extends DBContext {

    // 2. Create
    public int addPayment(Payment payment) {
        String getNextIdQuery = "SELECT COALESCE(MAX(id), 0) + 1 AS nextId FROM payments"; // Use COALESCE to handle empty table case
        try ( ResultSet rs = execSelectQuery(getNextIdQuery)) {
            if (rs.next()) {
                int nextId = rs.getInt("nextId");

                String sql = "INSERT INTO payments (id, user_id, voucher_id, total_amount, discount_amount, final_amount, address, phone, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                Object[] params = {
                    nextId,
                    payment.getUserId(),
                    payment.getVoucherId(),
                    payment.getTotalAmount(),
                    payment.getDiscountAmount(),
                    payment.getFinalAmount(),
                    payment.getAddress(),
                    payment.getPhone(),
                    payment.getStatus()
                };

                return execQuery(sql, params);
            }
        } catch (SQLException e) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, "Error adding payment: ", e);
        }
        return 0;
    }
    // Retrieve all payments from the database

    public ArrayList<Payment> getAll() {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT id, user_id, voucher_id, total_amount, discount_amount, final_amount, address, phone, payment_date, status FROM payments";

        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("voucher_id"),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount_amount"),
                        rs.getDouble("final_amount"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        // Convert java.sql.Date to java.time.LocalDate
                        rs.getString("payment_date"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving payments");
        }
        return payments;
    }

    public ArrayList<Payment> getAllByUserID(int userId) {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT id, user_id, voucher_id, total_amount, discount_amount, final_amount, address, phone, status FROM payments WHERE user_id = ?";

        try ( ResultSet rs = execSelectQuery(query, new Object[]{userId})) {
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("voucher_id"),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount_amount"),
                        rs.getDouble("final_amount"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving payments by user ID");
        }
        return payments;
    }

    public ArrayList<Payment> getPaymentsByStatus(String status) {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT id, user_id, voucher_id, total_amount, discount_amount, final_amount, address, phone, status FROM payments WHERE status = ?";

        try ( ResultSet rs = execSelectQuery(query, new Object[]{status})) {
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("voucher_id"),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount_amount"),
                        rs.getDouble("final_amount"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, "Error retrieving payments by status", ex);
        }
        return payments;
    }

    public ArrayList<Payment> getPaymentsByPage(int page, int pageSize) {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT id, user_id, voucher_id, total_amount, discount_amount, final_amount, address, phone, status "
                + "FROM payments "
                + "ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int offset = (page - 1) * pageSize;
        Object[] params = {offset, pageSize};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("voucher_id"),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount_amount"),
                        rs.getDouble("final_amount"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving payments with pagination");
        }
        return payments;
    }

    public int getTotalPaymentCount() {
        String query = "SELECT COUNT(*) AS total FROM payments";
        try ( ResultSet rs = execSelectQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving total payment count");
        }
        return 0;
    }

    public ArrayList<Payment> getPaymentsByStatusAndPage(String status, int page, int pageSize) {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT id, user_id, voucher_id, total_amount, discount_amount, final_amount, address, phone, status "
                + "FROM payments WHERE status = ? "
                + "ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int offset = (page - 1) * pageSize;
        Object[] params = {status, offset, pageSize};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("voucher_id"),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount_amount"),
                        rs.getDouble("final_amount"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, "Error retrieving filtered payments with pagination", ex);
        }
        return payments;
    }

    public int getTotalPaymentCountByStatus(String status) {
        String query = "SELECT COUNT(*) AS total FROM payments WHERE status = ?";
        try ( ResultSet rs = execSelectQuery(query, new Object[]{status})) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, "Error retrieving total payment count by status", ex);
        }
        return 0;
    }
    
    
    public int update(Payment payment) {
        String sql = "UPDATE payments SET status = ? WHERE id = ?";
        Object[] params = {
            payment.getStatus()
        };
        try {
            return execQuery(sql, params);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public Payment getOneById(int id) {
        String query = "SELECT id, user_id, voucher_id, total_amount, discount_amount, final_amount, address, phone, status FROM payments WHERE id = ?";
        Object[] params = {id};
        try (ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                return new Payment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("voucher_id"),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount_amount"),
                        rs.getDouble("final_amount"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving drink type by ID.");
        }
        return null;
    }

}

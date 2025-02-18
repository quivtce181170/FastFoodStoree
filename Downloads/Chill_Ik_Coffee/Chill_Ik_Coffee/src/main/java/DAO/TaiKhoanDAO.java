/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.DBContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TaiKhoan;

/**
 *
 * @author Do Van Luan - CE180457
 */
public class TaiKhoanDAO extends db.DBContext {

    // 1. Read
    public ArrayList<TaiKhoan> getAll() {

        ArrayList<TaiKhoan> taiKhoans = new ArrayList<>();
        String query = "select * from tai_khoan";

        try ( ResultSet rs = execSelectQuery(query)) {

            while (rs.next()) {
                taiKhoans.add(new TaiKhoan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Err");
        }
        return taiKhoans;
    }

    public static void main(String[] args) {
        TaiKhoanDAO tkdao = new TaiKhoanDAO();
        System.out.println(tkdao.getAll());
    }

    public TaiKhoan getOnlyById(int id) {
        String query = "select * from tai_khoan WHERE id = ?";
        Object[] params = {id};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                return new TaiKhoan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching Taikhoan: " + e.getMessage());
        }

        return null; // Trả về null nếu không tìm thấy hoặc có lỗi
    }

    // 2. Create
    // Cập nhật phương thức create trong TaiKhoanDAO
    public int create(TaiKhoan tk) {
        String getNextIdQuery = "SELECT MAX(id) + 1 AS nextId FROM tai_khoan m";
        try ( ResultSet rs = execSelectQuery(getNextIdQuery)) {
            if (rs.next()) {
                int nextId = rs.getInt(1);

                // Mã hóa mật khẩu trước khi lưu
                String hashedPassword = hashMd5(tk.getPassword());

                String createAccountQuery = "INSERT INTO tai_khoan (id, username, password, is_nhan_vien, is_admin) VALUES (?, ?, ?, ?, ?)";
                Object[] params = {nextId, tk.getUsername(), hashedPassword, tk.isIsNhanVien(), tk.isIsAdmin()};

                return execQuery(createAccountQuery, params);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return 0;
    }

// Hàm mã hóa MD5
    private String hashMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByUsername(String username) {
        String query = "SELECT COUNT(*) FROM tai_khoan WHERE username = ?";
        try ( ResultSet rs = execSelectQuery(query, new Object[]{username})) {
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Đã tồn tại
            }
        } catch (SQLException e) {
            System.out.println("Error while checking username existence: " + e.getMessage());
        }
        return false; // Không tồn tại
    }

    /*  // 3. Update
    public int update(TaiKhoan tk) {
        // Câu lệnh SQL yêu cầu 5 thuộc tính và điều kiện WHERE dựa trên id
        String sql = "UPDATE tai_khoan SET username = ?, password = ?, is_nhan_vien = ?, is_admin = ? WHERE id = ?";

        // Mã hóa mật khẩu trước khi lưu
        
        String hashedPassword = hashMd5(tk.getPassword());

        // Chuyển tất cả các thuộc tính của đối tượng taiKhoan vào mảng params
        Object[] params = {tk.getUsername(), hashedPassword, tk.isIsNhanVien() ? 1 : 0, tk.isIsAdmin() ? 1 : 0, tk.getId()};

        try {
            return execQuery(sql, params); // Gọi hàm thực thi câu truy vấn
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0; // Trả về 0 nếu có lỗi
        }
    }
     */
    public int update(TaiKhoan tk) {
        String password = tk.getPassword();

        // Nếu mật khẩu không thay đổi (trường mật khẩu trống), giữ nguyên mật khẩu cũ
        if (password == null || password.trim().isEmpty()) {
            // Lấy mật khẩu hiện tại của tài khoản từ CSDL
            TaiKhoan existingAccount = getOnlyById(tk.getId());
            password = existingAccount.getPassword();  // Giữ nguyên mật khẩu đã mã hóa
        } else {
            // Nếu có mật khẩu mới, thì mã hóa mật khẩu đó
            password = hashMd5(password);
        }

        // Tiến hành câu lệnh SQL để cập nhật thông tin tài khoản
        String sql = "UPDATE tai_khoan SET username = ?, password = ?, is_nhan_vien = ?, is_admin = ? WHERE id = ?";
        Object[] params = {tk.getUsername(), password, tk.isIsNhanVien() ? 1 : 0, tk.isIsAdmin() ? 1 : 0, tk.getId()};

        try {
            return execQuery(sql, params);  // Gọi hàm thực thi câu truy vấn
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;  // Trả về 0 nếu có lỗi
        }
    }

//4.delete
    public int delete(int id) {
        String deleteSql = "DELETE FROM tai_khoan WHERE id = ?";
        String renumberSql = "WITH CTE AS ("
                + "SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS new_id "
                + "FROM tai_khoan "
                + ") "
                + "UPDATE tai_khoan "
                + "SET id = CTE.new_id "
                + "FROM CTE "
                + "WHERE tai_khoan.id = CTE.id;";

        Connection conn = null;

        try {
            // Get the connection from DBContext
            conn = getConnection();
            conn.setAutoCommit(false); // Disable auto-commit

            // Delete the account
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
                    conn.close(); // Close the connection to free resources
                }
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    public ArrayList<TaiKhoan> getTaiKhoanByPage(int page, int pageSize) {
        ArrayList<TaiKhoan> taiKhoanList = new ArrayList<>();
        String query = "SELECT id, username, password, is_nhan_vien, is_admin "
                + "FROM tai_khoan "
                + "ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    taiKhoanList.add(new TaiKhoan(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getBoolean("is_nhan_vien"),
                            rs.getBoolean("is_admin")
                    ));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taiKhoanList;
    }

    public int getTotalTaiKhoan() {
        String query = "SELECT COUNT(*) FROM tai_khoan";
        try ( PreparedStatement ps = getConnection().prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}

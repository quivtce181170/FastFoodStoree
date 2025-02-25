package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TaiKhoan;

public class UserDAO extends db.DBContext {

    // Hàm kiểm tra đăng nhập
    public boolean login(String username, String password) {
        String sql = "SELECT COUNT(id) FROM tai_khoan WHERE username = ? AND password = ?";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, hashMd5(password)); // Mã hóa mật khẩu bằng MD5

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Tìm thấy người dùng
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Đăng nhập thất bại
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

    // Hàm kiểm tra đăng nhập và trả về đối tượng TaiKhoan nếu thành công
    public TaiKhoan AccTaiKhoanlogin(String username, String password) {
        String sql = "SELECT * FROM tai_khoan WHERE username = ? AND password = ?";
        try ( Connection conn = getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, hashMd5(password));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("is_nhan_vien"),
                        rs.getBoolean("is_admin")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }
}

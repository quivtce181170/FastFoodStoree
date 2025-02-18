package DAO;

import model.Voucher;
import db.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for managing Voucher database operations
 */
public class VoucherDAO extends DBContext {

    // 1. Read
    public ArrayList<Voucher> getAll() {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        String query = "SELECT * FROM voucher WHERE ngay_het_han >= CAST(GETDATE() AS DATE)";

        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                vouchers.add(new Voucher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("giam_gia"),
                        rs.getDate("ngay_het_han"),
                        rs.getInt("so_luong")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving vouchers");
        }
        return vouchers;
    }

    public Voucher getOnlyById(int id) {
        String query = "SELECT id, name, giam_gia, ngay_het_han, so_luong FROM voucher WHERE id = ?";
        Object[] params = {id};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                return new Voucher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("giam_gia"),
                        rs.getDate("ngay_het_han"),
                        rs.getInt("so_luong")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving voucher by ID");
        }

        return null;
    }

    // 2. Create
    public int create(Voucher voucher) {
        String getMaxIdQuery = "SELECT MAX(id) AS maxId FROM voucher";
        try ( ResultSet rs = execSelectQuery(getMaxIdQuery)) {
            int nextId = 1; // Start with ID 1
            if (rs.next()) {
                Integer maxId = rs.getInt("maxId");
                if (maxId != null) {
                    nextId = maxId + 1; // Increment if there are existing records
                }
            }

            String createVoucherQuery = "INSERT INTO voucher (id, name, giam_gia, ngay_het_han, so_luong) VALUES (?, ?, ?, ?, ?)";
            Object[] params = {
                nextId,
                voucher.getName(),
                voucher.getGiamGia(),
                voucher.getNgayHetHan(),
                voucher.getSoLuong()
            };
            return execQuery(createVoucherQuery, params);
        } catch (SQLException ex) {
            System.out.println("Error creating voucher: " + ex.getMessage());
        }
        return 0;

    }
// 3. Update

    public int update(Voucher voucher) {
        String sql = "UPDATE voucher SET name = ?, giam_gia = ?, ngay_het_han = ?, so_luong = ? WHERE id = ?";
        Object[] params = {
            voucher.getName(),
            voucher.getGiamGia(),
            voucher.getNgayHetHan(),
            voucher.getSoLuong(),
            voucher.getId()
        };
        try {
            return execQuery(sql, params);
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    // 4. Delete
    public int delete(int id) {
        String deleteSql = "DELETE FROM voucher WHERE id = ?";
        String renumberSql = "WITH CTE AS ("
                + "SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS new_id "
                + "FROM voucher "
                + ") "
                + "UPDATE voucher "
                + "SET id = CTE.new_id "
                + "FROM CTE "
                + "WHERE voucher.id = CTE.id;";

        Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Disable auto-commit

            Object[] params = {id};
            int result = execQuery(deleteSql, params);

            if (result > 0) {
                execQuery(renumberSql, null); // Only renumber IDs if deletion was successful
            }

            conn.commit();
            return result;
        } catch (SQLException ex) {
            System.out.println("Error during delete operation: " + ex.getMessage());
            if (conn != null) {
                try {
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

// 5. Giảm số lượng voucher
    public int reduceVoucherQuantity(int id) {
        String sql = "UPDATE voucher SET so_luong = so_luong - 1 WHERE id = ? AND so_luong > 0";
        Object[] params = {id};

        try {
            return execQuery(sql, params);
        } catch (SQLException ex) {
            System.out.println("Error reducing voucher quantity: " + ex.getMessage());
        }
        return 0;
    }
    // 6. Xóa các voucher đã hết hạn

    public void deleteExpiredVouchers() {
        String deleteExpiredSql = "DELETE FROM voucher WHERE ngay_het_han < CAST(GETDATE() AS DATE)";

        try {
            execQuery(deleteExpiredSql, null); // Thực hiện truy vấn xóa
        } catch (SQLException ex) {

            System.out.println("Error during deleting expired vouchers: " + ex.getMessage());
        }
    }

    public ArrayList<Voucher> getVouchersByPage(int page, int pageSize) {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        String query = "SELECT * FROM voucher WHERE ngay_het_han >= CAST(GETDATE() AS DATE) ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement stmt = getConnection().prepareStatement(query)) {
            int offset = (page - 1) * pageSize;
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vouchers.add(new Voucher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("giam_gia"),
                        rs.getDate("ngay_het_han"),
                        rs.getInt("so_luong")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vouchers;
    }

    public int getTotalVouchers() {
        String query = "SELECT COUNT(*) AS total FROM voucher WHERE ngay_het_han >= CAST(GETDATE() AS DATE)";
        try ( ResultSet rs = execSelectQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void deleteVouchersWithZeroQuantity() {
        // Truy vấn để xóa bản ghi trong các bảng tham chiếu trước
        String deleteFromReferencedTablesSql = "DELETE FROM gio_hang WHERE voucher_id IN (SELECT id FROM voucher WHERE so_luong = 0); "
                + "DELETE FROM payments WHERE voucher_id IN (SELECT id FROM voucher WHERE so_luong = 0);";
        // Truy vấn để xóa các voucher có số lượng bằng 0
        String deleteZeroQuantitySql = "DELETE FROM voucher WHERE so_luong = 0";

        try {
            // Xóa các bản ghi trong bảng tham chiếu trước
            execQuery(deleteFromReferencedTablesSql, null);

            // Xóa các voucher có số lượng bằng 0
            execQuery(deleteZeroQuantitySql, null);
        } catch (SQLException ex) {
            System.out.println("Error deleting vouchers with zero quantity: " + ex.getMessage());
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

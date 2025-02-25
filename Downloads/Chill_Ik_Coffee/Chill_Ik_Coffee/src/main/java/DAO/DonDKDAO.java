package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.donDK;

/**
 * DAO class for managing donDK records in the database. Handles creation of new records with automatic ID assignment.
 */
public class DonDKDAO extends db.DBContext {

    // Create new donDK record
    public int create(donDK dondk) {
        String getMaxIdQuery = "SELECT MAX(id) AS maxId FROM donDK";
        try ( ResultSet rs = execSelectQuery(getMaxIdQuery)) {
            int nextId = 1; // Start with ID 1
            if (rs.next()) {
                int maxId = rs.getInt("maxId");
                if (maxId != 0) { // If there is a max ID, increment it
                    nextId = maxId + 1;
                }
            }

            String createDonDKQuery = "INSERT INTO donDK (id, tenNhanVien, soDienThoai, diaChi, ngaySinh, queQuan) VALUES (?, ?, ?, ?, ?, ?)";
            Object[] params = {
                nextId,
                dondk.getTenNV(),
                dondk.getSdt(),
                dondk.getDiachi(),
                dondk.getNgaysinh(),
                dondk.getQuequan()
            };

            return execQuery(createDonDKQuery, params); // Execute query with parameters
        } catch (SQLException ex) {
            System.out.println("Error creating record in donDK: " + ex.getMessage());
        }
        return 0; // Return 0 if insertion fails
    }

    public ArrayList<donDK> getAll() {
        ArrayList<donDK> donDKs = new ArrayList<>();
        String query = "SELECT id, tenNhanVien, soDienThoai, diaChi, ngaySinh, queQuan, trangThai, ngayNhanDon FROM donDK";
        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                donDKs.add(new donDK(
                        rs.getInt("id"),
                        rs.getString("tenNhanVien"),
                        rs.getString("soDienThoai"),
                        rs.getString("diaChi"),
                        rs.getString("ngaySinh"),
                        rs.getString("queQuan"),
                        rs.getString("trangThai"),
                        rs.getString("ngayNhanDon")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonDKDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving donDK records");
        }
        return donDKs;
    }

}

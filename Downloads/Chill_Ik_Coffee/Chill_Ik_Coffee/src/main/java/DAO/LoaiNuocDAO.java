package DAO;

import db.DBContext;
import model.LoaiNuoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to manage operations on the loai_nuoc table.
 * 
 * @author Vo Truong Qui - CE181170
 */
public class LoaiNuocDAO extends DBContext {

    /**
     * Retrieves all types of drinks from the loai_nuoc table.
     * 
     * @return an ArrayList of LoaiNuoc objects.
     */
    public ArrayList<LoaiNuoc> getAll() {
        ArrayList<LoaiNuoc> loaiNuocs = new ArrayList<>();
        String query = "SELECT id, loai_nuoc FROM loai_nuoc"; // Đảm bảo tên cột là loai_nuoc

        try (ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                loaiNuocs.add(new LoaiNuoc(
                    rs.getInt("id"),
                    rs.getString("loai_nuoc") // Đảm bảo tên cột là loai_nuoc
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiNuocDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving types of drinks.");
        }
        return loaiNuocs;
    }

    /**
     * Retrieves a type of drink by its ID.
     * 
     * @param id the ID of the drink type to retrieve.
     * @return the LoaiNuoc object, or null if not found.
     */
    public LoaiNuoc getOneById(int id) {
        String query = "SELECT id, loai_nuoc FROM loai_nuoc WHERE id = ?";
        Object[] params = {id};
        try (ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                return new LoaiNuoc(
                        rs.getInt("id"),
                        rs.getString("loai_nuoc") // Đảm bảo tên cột là loai_nuoc
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving drink type by ID.");
        }
        return null;
    }

    /**
     * 
     * @param loaiNuoc
     * @return
     */
 public int create(LoaiNuoc loaiNuoc) {
    // Query to get the next available ID
    String getNextIdQuery = "SELECT COALESCE(MAX(id), 0) + 1 AS nextId FROM loai_nuoc";
    
    try (ResultSet rs = execSelectQuery(getNextIdQuery)) {
        if (rs.next()) {
            int nextId = rs.getInt("nextId");
            // Set the generated ID to the LoaiNuoc object (optional if needed elsewhere)
            loaiNuoc.setId(nextId);
            
            // Query to insert new LoaiNuoc record
            String createLoaiNuocQuery = "INSERT INTO loai_nuoc (id, loai_nuoc) VALUES (?, ?)";
            Object[] params = {nextId, loaiNuoc.getLoaiNuoc()};
            
            // Execute the insertion query
            return execQuery(createLoaiNuocQuery, params);
        }
    } catch (SQLException ex) {
        System.out.println("Error creating type of drink.");
        Logger.getLogger(LoaiNuocDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
} 


    /**
     * Updates an existing type of drink in the loai_nuoc table.
     * 
     * @param loaiNuoc the LoaiNuoc object with updated information.
     * @return the number of affected rows.
     */
    public int update(LoaiNuoc loaiNuoc) {
        String sql = "UPDATE loai_nuoc SET loai_nuoc = ? WHERE id = ?";
        Object[] params = {loaiNuoc.getLoaiNuoc(), loaiNuoc.getId()};
        try {
            return execQuery(sql, params);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiNuocDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * Deletes a type of drink from the loai_nuoc table by its ID.
     * 
     * @param id the ID of the drink type to delete.
     * @return the number of affected rows.
     */
    public int delete(int id) {
        String sql = "DELETE FROM loai_nuoc WHERE id = ?";
        Object[] params = {id};
        try {
            return execQuery(sql, params);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiNuocDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    } 
}

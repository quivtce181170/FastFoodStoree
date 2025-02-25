package DAO;

import db.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DoanhThu;

public class DoanhThuDAO extends DBContext {

    public ArrayList<DoanhThu> getTongDoanhThuTheoNgay() {
        ArrayList<DoanhThu> doanhThuList = new ArrayList<>();
        String sql = "SELECT "
                + "CONVERT(VARCHAR(10), p.payment_date, 120) AS Ngay, "
                + "SUM(p.final_amount) AS DoanhThuNgay "
                + "FROM payments p "
                + "WHERE p.payment_date IS NOT NULL "
                + "GROUP BY CONVERT(VARCHAR(10), p.payment_date, 120) "
                + "ORDER BY Ngay";

        try {
            ResultSet rs = execSelectQuery(sql);
            while (rs.next()) {
                doanhThuList.add(new DoanhThu(
                        null,
                        rs.getString("Ngay"),
                        rs.getDouble("DoanhThuNgay")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuList;
    }

    public ArrayList<DoanhThu> getTongDoanhThuTheoTuan() {
        ArrayList<DoanhThu> doanhThuList = new ArrayList<>();
        String sql = "SELECT DATEPART(YEAR, payment_date) AS Nam, "
                + "DATEPART(WEEK, payment_date) AS Tuan, "
                + "SUM(final_amount) AS DoanhThuTuan "
                + "FROM payments "
                + "WHERE payment_date IS NOT NULL "
                + "GROUP BY DATEPART(YEAR, payment_date), DATEPART(WEEK, payment_date) "
                + "ORDER BY Nam, Tuan";

        try {
            ResultSet rs = execSelectQuery(sql);
            while (rs.next()) {
                doanhThuList.add(new DoanhThu(
                        null,
                        "Tuần " + rs.getInt("Tuan") + " Năm " + rs.getInt("Nam"),
                        rs.getDouble("DoanhThuTuan")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuList;
    }

    public ArrayList<DoanhThu> getTongDoanhThuTheoThang() {
        ArrayList<DoanhThu> doanhThuList = new ArrayList<>();
        String sql = "SELECT FORMAT(payment_date, 'yyyy-MM') AS Thang, "
                + "SUM(final_amount) AS DoanhThuThang "
                + "FROM payments "
                + "WHERE payment_date IS NOT NULL "
                + "GROUP BY FORMAT(payment_date, 'yyyy-MM') "
                + "ORDER BY Thang";

        try {
            ResultSet rs = execSelectQuery(sql);
            while (rs.next()) {
                doanhThuList.add(new DoanhThu(
                        null,
                        rs.getString("Thang"),
                        rs.getDouble("DoanhThuThang")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuList;
    }

    public ArrayList<DoanhThu> getTongDoanhThuTheoNam() {
        ArrayList<DoanhThu> doanhThuList = new ArrayList<>();
        String sql = "SELECT YEAR(payment_date) AS year, "
                + "SUM(final_amount) AS total_revenue "
                + "FROM payments "
                + "WHERE payment_date IS NOT NULL "
                + "GROUP BY YEAR(payment_date) "
                + "ORDER BY year";

        try {
            ResultSet rs = execSelectQuery(sql);
            while (rs.next()) {
                doanhThuList.add(new DoanhThu(
                        null,
                        String.valueOf(rs.getInt("year")),
                        rs.getDouble("total_revenue")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuList;
    }
}

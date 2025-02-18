/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vo Truong Qui - CE181170
 */
import java.sql.Date;

public class DoanhThu {

    private String tenMonNuoc;
    private String thoiGian; // Có thể là Ngày, Tuần hoặc Tháng
    private double doanhThu;

    public DoanhThu(String tenMonNuoc, String thoiGian, double doanhThu) {
        this.tenMonNuoc = tenMonNuoc;
        this.thoiGian = thoiGian;
        this.doanhThu = doanhThu;
    }

    public String getTenMonNuoc() {
        return tenMonNuoc;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public double getDoanhThu() {
        return doanhThu;
    }
}

package model;

import java.sql.Date;

public class Voucher {

    private int id = 0;
    private String name;
    private double giamGia; // Tỷ lệ giảm giá
    private Date ngayHetHan; // Ngày hết hạn (java.sql.Date để tương thích với SQL)

    private int soLuong; // Số lượng voucher

    // Constructor
    public Voucher(int id, String name, double giamGia, Date ngayHetHan, int soLuong) {
        this.id = id;
        this.name = name;
        this.giamGia = giamGia;
        this.ngayHetHan = ngayHetHan;

        this.soLuong = soLuong; // Khởi tạo số lượng
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public int getSoLuong() {
        return soLuong; // Getter cho số lượng
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong; // Setter cho số lượng
    }
}

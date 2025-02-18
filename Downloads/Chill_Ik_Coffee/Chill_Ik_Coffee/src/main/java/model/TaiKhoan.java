/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Phan Hồng Tài - CE181490
 */
public class TaiKhoan {

    private int id;
    private String username;
    private String password;
    private boolean isNhanVien;
    private boolean isAdmin;

    public TaiKhoan(int id, String username, String password, boolean isNhanVien, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isNhanVien = isNhanVien;
        this.isAdmin = isAdmin;
    }

    public TaiKhoan(String username, String password, boolean isNhanVien, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isNhanVien = isNhanVien;
        this.isAdmin = isAdmin;
    }

    public TaiKhoan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsNhanVien() {
        return isNhanVien;
    }

    public void setIsNhanVien(boolean isNhanVien) {
        this.isNhanVien = isNhanVien;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "id=" + id + ", username=" + username + ", password=" + password + ", isNhanVien=" + isNhanVien + ", isAdmin=" + isAdmin + '}';
    }

}

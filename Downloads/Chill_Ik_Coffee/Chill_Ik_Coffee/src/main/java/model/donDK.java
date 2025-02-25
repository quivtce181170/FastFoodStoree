package model;

/**
 * Class representing a registration form.
 */
public class donDK {

    private int id;
    private String tenNV;
    private String Sdt;
    private String diachi;
    private String ngaysinh;
    private String quequan;
    private String trangThai; // Trạng thái của đơn đăng ký
    private String ngayNhanDon; // Ngày nhận đơn

    // Constructor với tất cả các thuộc tính
    public donDK(int id, String tenNV, String Sdt, String diachi, String ngaysinh, String quequan, String trangThai, String ngayNhanDon) {
        this.id = id;
        this.tenNV = tenNV;
        this.Sdt = Sdt;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.trangThai = trangThai; // Lưu trạng thái
        this.ngayNhanDon = ngayNhanDon;
    }

    public donDK(int id, String tenNV, String Sdt, String diachi, String ngaysinh, String quequan) {
        this.id = id;
        this.tenNV = tenNV;
        this.Sdt = Sdt;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
    }

    
    // Constructor không tham số
    public donDK() {
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai; // Để có thể cập nhật trạng thái
    }

    public String getNgayNhanDon() {
        return ngayNhanDon;
    }

    public void setNgayNhanDon(String ngayNhanDon) {
        this.ngayNhanDon = ngayNhanDon;
    }
}

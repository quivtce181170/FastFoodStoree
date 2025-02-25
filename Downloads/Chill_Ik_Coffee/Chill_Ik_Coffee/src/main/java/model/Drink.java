/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Ngoc Phat - CE180321
 */
public class Drink {

    private int id;
    private String name;
    private int gia;
    private int soluong;
    private int loainuocid;
    private boolean trangthai;
    private String imageUrl;

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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getLoainuocid() {
        return loainuocid;
    }

    public void setLoainuocid(int loainuocid) {
        this.loainuocid = loainuocid;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Drink() {
    }

    public Drink(int id, String name, int gia, int soluong, int loainuocid, boolean trangthai, String imageUrl) {
        this.id = id;
        this.name = name;
        this.gia = gia;
        this.soluong = soluong;
        this.loainuocid = loainuocid;
        this.trangthai = trangthai;
        this.imageUrl = imageUrl;
    }

    public Drink(String name, int gia, int soluong, int loainuocid, boolean trangthai, String imageUrl) {
        this.name = name;
        this.gia = gia;
        this.soluong = soluong;
        this.loainuocid = loainuocid;
        this.trangthai = trangthai;
        this.imageUrl = imageUrl;
    }
}

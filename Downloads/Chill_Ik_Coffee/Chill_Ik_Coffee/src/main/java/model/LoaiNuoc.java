/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vo Truong Qui - CE181170
 */
public class LoaiNuoc {

    private int id;
    private String loaiNuoc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaiNuoc() {
        return loaiNuoc;
    }

    public void setLoaiNuoc(String loaiNuoc) {
        this.loaiNuoc = loaiNuoc;
    }

    public LoaiNuoc(int id, String loaiNuoc) {
        this.id = id;
        this.loaiNuoc = loaiNuoc;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Do Van Luan - CE180457
 */
public class Payment {

    private int id;
    private int userId;
    private int voucherId;
    private double totalAmount;
    private double discountAmount;
    private double finalAmount;
    private String address;
    private String phone;
    private String  paymentDate;
    private String status;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Payment(int id, int userId, int voucherId, double totalAmount, double discountAmount, double finalAmount, String address, String phone, String paymentDate, String status) {
        this.id = id;
        this.userId = userId;
        this.voucherId = voucherId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.address = address;
        this.phone = phone;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment(int id, String status) {
        this.id = id;
        this.status = status;
    }

    

    
    
    


    public Payment() {
    }

    

    public String getStatus() {
        return status;
    }

    public Payment(int id, int userId, int voucherId, double totalAmount, double discountAmount, double finalAmount, String address, String phone, String status) {
        this.id = id;
        this.userId = userId;
        this.voucherId = voucherId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

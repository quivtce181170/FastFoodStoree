/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vo Truong Qui - CE181170
 */
public class DeliveryManager {
 public class Order {
    private int orderId;
    private Integer deliveryStaffId;  // Có thể null

    // Constructor
    public Order(int orderId, Integer deliveryStaffId) {
        this.orderId = orderId;
        this.deliveryStaffId = deliveryStaffId;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public Integer getDeliveryStaffId() { return deliveryStaffId; }
    public void setDeliveryStaffId(Integer deliveryStaffId) { this.deliveryStaffId = deliveryStaffId; }
}

    
}

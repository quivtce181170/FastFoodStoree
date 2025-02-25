/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Vo Truong Qui - CE181170
 */
public class Inventory {

    private int inventoryId;          // inventory_id
    private int dishId;
    private String dishName;
    private String supplierName;      // supplier_name
    private String contactInfo;       // contact_info
    private int quantity;             // quantity
    private double purchasePrice;     // purchase_price
    private double sellingPrice;      // selling_price
    private Date createdAt;           // created_at

    public Inventory(int inventoryId, int dishId, String supplierName, String contactInfo, int quantity, double purchasePrice, double sellingPrice, Date createdAt) {
        this.inventoryId = inventoryId;
        this.dishId = dishId;
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.createdAt = createdAt;
    }

    public Inventory(int inventoryId, String dishName,  int quantity, String supplierName, String contactInfo,double purchasePrice, double sellingPrice, Date createdAt) {
        this.inventoryId = inventoryId;
        this.dishName = dishName;
         this.quantity = quantity;
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.createdAt = createdAt;
    }

  
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}

package model;

/**
 * Model class for CartItem.
 */
public class CartItem {
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;

    public CartItem(String productName, int quantity, double price, double totalPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    // Getters and setters

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

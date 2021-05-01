package com.clicktocart.app.payload.response;

public class CartResponse {

    private int id;
    private String itemName;
    private int qty;

    public int getId() {  return id; }

    public void setId(int id) { this.id = id; }

    private double totalPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

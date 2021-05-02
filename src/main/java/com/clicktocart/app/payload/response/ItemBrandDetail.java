package com.clicktocart.app.payload.response;

public class ItemBrandDetail {

    private String brand;
    private int quantity;

    public ItemBrandDetail() {
    }

    public ItemBrandDetail(String brand, int quantity) {
        this.brand = brand;
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

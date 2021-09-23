package com.example.cartandprofile;

public class ShowCart_Model {
    private String Product_name;
    private String Price;

    public ShowCart_Model() {
    }

    public ShowCart_Model(String product_name, String price) {
        Product_name = product_name;
        Price = price;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}

package com.example.toyzproject;

public class Toys
{
    private String ToyID;
    private String ToyName;
    private String ToyDescription;
    private String ToyColor;
    private String ToyBrand;
    private String ToyModel;
    private String ToyPrice;
    private String ToyDate;

    public Toys(){}

    public Toys(String toyID, String toyName, String toyDescription, String toyColor, String toyBrand, String toyModel, String toyPrice, String toyDate)
    {
        this.ToyID = toyID;
        this.ToyName = toyName;
        this.ToyDescription = toyDescription;
        this.ToyColor = toyColor;
        this.ToyBrand = toyBrand;
        this.ToyModel = toyModel;
        this.ToyPrice = toyPrice;
        this.ToyDate = toyDate;
    }

    public String getToyID() {
        return ToyID;
    }

    public void setToyID(String toyID) {
        ToyID = toyID;
    }

    public String getToyName() {
        return ToyName;
    }

    public void setToyName(String toyName) {
        ToyName = toyName;
    }

    public String getToyDescription() {
        return ToyDescription;
    }

    public void setToyDescription(String toyDescription) {
        ToyDescription = toyDescription;
    }

    public String getToyColor() {
        return ToyColor;
    }

    public void setToyColor(String toyColor) {
        ToyColor = toyColor;
    }

    public String getToyBrand() {
        return ToyBrand;
    }

    public void setToyBrand(String toyBrand) {
        ToyBrand = toyBrand;
    }

    public String getToyModel() {
        return ToyModel;
    }

    public void setToyModel(String toyModel) {
        ToyModel = toyModel;
    }

    public String getToyPrice() {
        return ToyPrice;
    }

    public void setToyPrice(String toyPrice) {
        ToyPrice = toyPrice;
    }

    public String getToyDate() {
        return ToyDate;
    }

    public void setToyDate(String toyDate) {
        ToyDate = toyDate;
    }
}

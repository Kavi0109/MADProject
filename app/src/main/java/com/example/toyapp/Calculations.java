package com.example.toyapp;

public class Calculations {
    protected String calculatepercentageDonation(Float value,Float val) {
        Float ans = (value / val) * 100;
        String s=String.format("%.2f",ans);
        return s;
    }
}

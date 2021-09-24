package com.example.toyapp;

public class Feedback {

    private String inquiry_type;
    private String suggestion;
    private String emailAdd;

    public Feedback() {
    }

    public Feedback(String inquiry_type, String suggestion, String emailAdd) {
        this.inquiry_type = inquiry_type;
        this.suggestion = suggestion;
        this.emailAdd = emailAdd;
    }

    public String getInquiry_type() {
        return inquiry_type;
    }

    public void setInquiry_type(String inquiry_type) {
        this.inquiry_type = inquiry_type;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }
}

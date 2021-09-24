package com.example.toyapp;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Organization implements Serializable {

    @Exclude
    private String key;
    private String orgName;
    private String orgAddress;
    private String orgEmail;
 //   private Integer orgPhone;
    private String donationType;
    private String donationGender;
    private String quantity;
    private String ageGroup;
    private String donationPeriod;

    public Organization() {
    }

    public Organization(String orgName, String orgAddress, String orgEmail, String donationType, String donationGender, String quantity, String ageGroup, String donationPeriod) {
        this.orgName = orgName;
        this.orgAddress = orgAddress;
        this.orgEmail = orgEmail;
      //  this.orgPhone = orgPhone;
        this.donationType = donationType;
        this.donationGender = donationGender;
        this.quantity = quantity;
        this.ageGroup = ageGroup;
        this.donationPeriod = donationPeriod;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    //public Integer getOrgPhone() {
   //     return orgPhone;
   // }

   // public void setOrgPhone(Integer orgPhone) {
   //     this.orgPhone = orgPhone;
  //  }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getDonationGender() {
        return donationGender;
    }

    public void setDonationGender(String donationGender) {
        this.donationGender = donationGender;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getDonationPeriod() {
        return donationPeriod;
    }

    public void setDonationPeriod(String donationPeriod) {
        this.donationPeriod = donationPeriod;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

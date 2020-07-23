package com.store.writers.model.entity;

import javax.persistence.*;

@Entity
public class UserBilling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userBillingName;
    private String userBillingStreet;
    private String userBillingCity;
    private String userBillingProvince;
    private String userBillingCountry;
    private String userBillingCode;

    @OneToOne(cascade = CascadeType.ALL)
    private UserPayment userPayment;

    public Long getId() {
        return id;
    }

    public String getUserBillingName() {
        return userBillingName;
    }

    public void setUserBillingName(String userBillingName) {
        this.userBillingName = userBillingName;
    }

    public String getUserBillingStreet() {
        return userBillingStreet;
    }

    public void setUserBillingStreet(String userBillingStreet) {
        this.userBillingStreet = userBillingStreet;
    }

    public String getUserBillingCity() {
        return userBillingCity;
    }

    public void setUserBillingCity(String userBillingCity) {
        this.userBillingCity = userBillingCity;
    }

    public String getUserBillingProvince() {
        return userBillingProvince;
    }

    public void setUserBillingProvince(String userBillingProvince) {
        this.userBillingProvince = userBillingProvince;
    }

    public String getUserBillingCountry() {
        return userBillingCountry;
    }

    public void setUserBillingCountry(String userBillingCountry) {
        this.userBillingCountry = userBillingCountry;
    }

    public String getUserBillingCode() {
        return userBillingCode;
    }

    public void setUserBillingCode(String userBillingCode) {
        this.userBillingCode = userBillingCode;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }
}

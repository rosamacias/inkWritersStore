package com.store.writers.model.entity;

import javax.persistence.*;

@Entity
public class UserShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userShippingName;
    private String userShippingStreet;
    private String userShippingCity;
    private String userShippingProvince;
    private String userShippingCountry;
    private String userShippingCode;

    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public String getUserShippingName() {
        return userShippingName;
    }

    public void setUserShippingName(String userShippingName) {
        this.userShippingName = userShippingName;
    }

    public String getUserShippingStreet() {
        return userShippingStreet;
    }

    public void setUserShippingStreet(String userShippingStreet) {
        this.userShippingStreet = userShippingStreet;
    }

    public String getUserShippingCity() {
        return userShippingCity;
    }

    public void setUserShippingCity(String userShippingCity) {
        this.userShippingCity = userShippingCity;
    }

    public String getUserShippingProvince() {
        return userShippingProvince;
    }

    public void setUserShippingProvince(String userShippingState) {
        this.userShippingProvince = userShippingProvince;
    }

    public String getUserShippingCountry() {
        return userShippingCountry;
    }

    public void setUserShippingCountry(String userShippingCountry) {
        this.userShippingCountry = userShippingCountry;
    }

    public String getUserShippingCode() {
        return userShippingCode;
    }

    public void setUserShippingCode(String userShippingCode) {
        this.userShippingCode = userShippingCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}

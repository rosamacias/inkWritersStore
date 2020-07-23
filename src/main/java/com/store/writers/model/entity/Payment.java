package com.store.writers.model.entity;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private String cardName;
    private String cardNumber;
    private int expireMounth;
    private int expireYear;
    private int cvc;

    private String holderName;

    @OneToOne
    private Order order;

    @OneToOne
    private UserBilling userBilling;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpireMounth() {
        return expireMounth;
    }

    public void setExpireMounth(int expireMounth) {
        this.expireMounth = expireMounth;
    }

    public int getGetExpireYear() {
        return expireYear;
    }

    public void setGetExpireYear(int expireYear) {
        this.expireYear = expireYear;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

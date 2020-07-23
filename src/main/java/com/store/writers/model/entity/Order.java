package com.store.writers.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "UserOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate orderDate;
    private LocalDate shippingDate;
    private String shippingMethod;
    private String orderState;
    private BigDecimal orderTotal;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;

    @OneToMany(mappedBy = "cartItem")
    private List<ItemToCartItem> itemToCartItemList;

    @OneToOne(cascade = CascadeType.ALL)
    private ShippingAddress shipppingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public ShippingAddress getShipppingAddress() {
        return shipppingAddress;
    }

    public void setShipppingAddress(ShippingAddress shipppingAddress) {
        this.shipppingAddress = shipppingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

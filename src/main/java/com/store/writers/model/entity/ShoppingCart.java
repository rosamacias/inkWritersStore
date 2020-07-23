package com.store.writers.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItemList;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    private BigDecimal grandTotal;

    public Long getId() {
        return id;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void addCartItem(CartItem item)
    {
        this.cartItemList.add(item);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

}

package com.store.writers.service;

import com.store.writers.model.entity.ShoppingCart;

import java.math.BigDecimal;

public interface ShoppingCartService {

    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
    BigDecimal  getTotal(ShoppingCart shoppingCart);
}

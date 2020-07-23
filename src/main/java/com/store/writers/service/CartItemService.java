package com.store.writers.service;

import com.store.writers.model.entity.CartItem;
import com.store.writers.model.entity.Item;
import com.store.writers.model.entity.ShoppingCart;
import com.store.writers.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    CartItem updateCartItem(CartItem cartItem);
    Optional<CartItem> findById(Long id);
    CartItem addItemToCartItem(Item item, User user, int qty);

}

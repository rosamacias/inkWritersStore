package com.store.writers.repository;

import com.store.writers.model.entity.CartItem;
import com.store.writers.model.entity.Item;
import com.store.writers.model.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}

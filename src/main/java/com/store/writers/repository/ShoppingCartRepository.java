package com.store.writers.repository;

import com.store.writers.model.entity.Item;
import com.store.writers.model.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
}

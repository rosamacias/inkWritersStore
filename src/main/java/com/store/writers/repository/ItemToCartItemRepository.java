package com.store.writers.repository;

import com.store.writers.model.entity.CartItem;
import com.store.writers.model.entity.ItemToCartItem;
import org.springframework.data.repository.CrudRepository;

public interface ItemToCartItemRepository  extends CrudRepository<ItemToCartItem, Long> {
}

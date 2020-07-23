package com.store.writers.repository;

import com.store.writers.model.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAll();
    List<Item> findAllByCategory(String category);
}

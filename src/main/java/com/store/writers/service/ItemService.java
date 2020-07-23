package com.store.writers.service;

import com.store.writers.model.entity.Item;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> findAll();
    List<Item> findAllByCategory(String category);
    Optional<Item> findById(Long id);
}

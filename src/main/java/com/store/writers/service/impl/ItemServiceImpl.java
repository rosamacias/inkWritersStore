package com.store.writers.service.impl;

import com.store.writers.model.entity.Item;
import com.store.writers.repository.ItemRepository;
import com.store.writers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findAllByCategory(String category) {
        return itemRepository.findAllByCategory(category);
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

}

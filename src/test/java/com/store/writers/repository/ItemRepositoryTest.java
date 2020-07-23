package com.store.writers.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void findAll() {
    }

    @Test
    void findAllByCategory() {
    }



}
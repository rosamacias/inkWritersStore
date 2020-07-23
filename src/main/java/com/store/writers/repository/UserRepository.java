package com.store.writers.repository;

import com.store.writers.model.entity.Item;
import com.store.writers.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

}

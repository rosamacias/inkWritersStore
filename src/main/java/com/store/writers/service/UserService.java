package com.store.writers.service;

import com.store.writers.model.Role;
import com.store.writers.model.entity.User;

public interface UserService {

    Iterable<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    User createUser(User user, Role roles);
    User createCustomerUser(User user);
    void save(User user);
}

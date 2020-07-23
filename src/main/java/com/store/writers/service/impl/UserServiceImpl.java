package com.store.writers.service.impl;

import com.store.writers.model.entity.*;
import com.store.writers.model.Role;
import com.store.writers.repository.ShoppingCartRepository;
import com.store.writers.repository.UserRepository;
import com.store.writers.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User createUser(User user, Role role) {

        User usr = userRepository.findByUsername(user.getUsername());

        if (usr != null) {
            LOG.info("User [ " + user.getUsername() + " ] already exists.");
        } else {
            user.setRole(role);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);

            user.setUserShippingList(new ArrayList<UserShipping>());
            user.setUserPaymentList(new ArrayList<UserPayment>());

            usr = userRepository.save(user);
        }
        return usr;
    }



    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User createCustomerUser(User user) {
        return this.createUser(user, Role.CUSTOMER);
    }
}
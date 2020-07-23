package com.store.writers.service.impl;

import com.store.writers.model.entity.*;
import com.store.writers.repository.CartItemRepository;
import com.store.writers.repository.ItemToCartItemRepository;
import com.store.writers.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemToCartItemRepository itemToCartItemRepository;

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        BigDecimal price = new BigDecimal(cartItem.getItem().getPrice())
                .multiply(new BigDecimal(cartItem.getQuantity()));

        cartItem.setSubtotal(price.setScale(2, RoundingMode.HALF_UP));
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    public CartItem addItemToCartItem(Item item, User user, int qty)
    {
        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
        for (CartItem cartItem : cartItemList) {
            if (item.getId() == cartItem.getItem().getId()) //already in the shopping cart
            {
                cartItem.setQuantity(cartItem.getQuantity() + qty);
                cartItem.setSubtotal(new BigDecimal(item.getPrice()).multiply(new BigDecimal(qty)));
                cartItemRepository.save(cartItem);
                return cartItem;
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setItem(item);
        cartItem.setQuantity(qty);
        cartItem.setSubtotal(new BigDecimal(item.getPrice()).multiply(new BigDecimal(qty)));
        cartItemRepository.save(cartItem);

        ItemToCartItem itemToCartItem = new ItemToCartItem();
        itemToCartItem.setItem(item);
        itemToCartItem.setCartItem(cartItem);
        itemToCartItemRepository.save(itemToCartItem);

        return cartItem;
    }


    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }


}

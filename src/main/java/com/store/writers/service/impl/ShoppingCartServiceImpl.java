package com.store.writers.service.impl;

import com.store.writers.model.entity.CartItem;
import com.store.writers.model.entity.ShoppingCart;
import com.store.writers.repository.ShoppingCartRepository;
import com.store.writers.service.ShoppingCartService;
import com.store.writers.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart)
    {
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getItem().getUnitsInStock() > 0) {
                cartItemService.updateCartItem(cartItem);
            }
        }
        shoppingCart.setGrandTotal(getTotal(shoppingCart));
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public BigDecimal getTotal(ShoppingCart shoppingCart)
    {
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        BigDecimal cartTotal = new BigDecimal(0);
        cartItemList.forEach(cartItem -> cartTotal.add( cartItem.getSubtotal()));
        return cartTotal;
    }

}

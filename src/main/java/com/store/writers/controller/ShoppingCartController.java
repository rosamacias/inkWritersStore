package com.store.writers.controller;

import com.store.writers.model.entity.CartItem;
import com.store.writers.model.entity.Item;
import com.store.writers.model.entity.ShoppingCart;
import com.store.writers.model.entity.User;
import com.store.writers.service.CartItemService;
import com.store.writers.service.ItemService;
import com.store.writers.service.ShoppingCartService;
import com.store.writers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping ("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public ModelAndView shoppingCart() {

        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        User user = userService.findByUsername(getPrincipal());
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<CartItem> cartItemList =  cartItemService.findByShoppingCart(shoppingCart);

        modelAndView.addObject("cartItemList", cartItemList);

        BigDecimal cartTotal = new BigDecimal(0);
        for(CartItem cartItem : cartItemList)
            cartTotal = cartTotal.add( cartItem.getSubtotal());

        modelAndView.addObject("total", cartTotal);
        modelAndView.addObject("user", user);
        modelAndView.addObject("id", shoppingCart.getId());

        return modelAndView;
    }

    @RequestMapping("/addItem")
    public String addItemToCart(@PathParam("id") Long id,
                                      @RequestParam("quantity") int quantity,
                                      Model model) {

        User user = userService.findByUsername(getPrincipal());
        if (user == null)
            return "/login";

        Optional<Item> itemOpt = itemService.findById(id);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();

            if (quantity > item.getUnitsInStock()) {
                model.addAttribute("outOfStockMessage", "Not enough in stock");
                return "forward:/catalog";
            }

            CartItem cartItem = cartItemService.addItemToCartItem(item, user, quantity);
            model.addAttribute("addItemSuccess", true);
        }
        return "forward:/catalog";
    }

    @GetMapping("/removeProduct")
    public ModelAndView removeCartItemFromCart(@PathVariable("id") Long id) {

        return shoppingCart();
    }


    private User getLoggedUser(String username)
    {
       return userService.findByUsername(username);
    }

    private String getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return
                    authentication.getName();
        }
        return null;
    }


}

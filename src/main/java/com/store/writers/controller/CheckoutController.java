package com.store.writers.controller;

import com.store.writers.model.entity.*;
import com.store.writers.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class CheckoutController {

    private ShippingAddress shippingAddress = new ShippingAddress();
    private BillingAddress  billingAddress = new BillingAddress();
    private Payment payment = new Payment();

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BillingAddressService billingAddressService;

    @RequestMapping("/checkout")
    public String checkout(@RequestParam("id") Long id,
                           @RequestParam(value="missingRequiredFiels", required=false) boolean missingRequiredFields,
                           Model model,
                           Principal principal)
    {
        User user = userService.findByUsername(principal.getName());
        if (id != user.getShoppingCart().getId())
            return "badRequestPage";

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
        if (cartItemList.isEmpty())
        {
            model.addAttribute("emptyCart", true);
            return "forward:/shoppingCart/cart";
        }
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getItem().getUnitsInStock() < cartItem.getQuantity()) {
                model.addAttribute("notEnoughInStock", true);
                return "forward:/shoppingCart/cart";
            }
        }

        List<UserShipping> userShippingList = user.getUserShippingList();
        List<UserPayment>  userPaymentList  = user.getUserPaymentList();

        model.addAttribute("userShippingList",  userShippingList);
        model.addAttribute("userPaymentList",   userPaymentList);
        model.addAttribute("emptyShippingList", userShippingList.isEmpty());
        model.addAttribute("emptyPaymentList",  userPaymentList.isEmpty());

        ShoppingCart shoppingCart = user.getShoppingCart();
        for (UserShipping userShipping : userShippingList)
            if(userShipping.isDefault())
            {
                shippingAddressService.setByUserShipping(userShipping, shippingAddress);
            }
        for (UserPayment userPayment : userPaymentList)
            if(userPayment.isDefaultPayment())
            {
                paymentService.setByUserPayment(userPayment, payment);
                billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
            }

        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("billingAddress", billingAddress);
        model.addAttribute("payment", payment);
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", user.getShoppingCart());

        return "checkout";
    }
}

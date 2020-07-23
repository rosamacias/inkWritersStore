package com.store.writers.controller;

import com.store.writers.model.entity.Item;
import com.store.writers.model.entity.User;
import com.store.writers.service.ItemService;
import com.store.writers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CatalogController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;


    @RequestMapping("/catalog")
    public String catalog(Model model, Principal principal) {
        List<Item> items = itemService.findAll();
        model.addAttribute("catalog", items);

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "catalog/index";
    }

    @RequestMapping("/catalogItem")
    public String catalogItem(@PathParam("id") Long id, Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        Optional<Item> itemOptional = itemService.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            model.addAttribute("item", item);

            List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            model.addAttribute("qtyList", qtyList);
            model.addAttribute("quantity", 1);
            model.addAttribute("item", item);
        }
        return "catalog/catalogItem";
    }

}

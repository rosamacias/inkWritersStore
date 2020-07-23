package com.store.writers.controller;

import com.store.writers.model.entity.User;
import com.store.writers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AdminController {

   @Autowired
   private UserService userService;

   @RequestMapping("/admin")
   public String admin()
   {
       return "admin/index";
   }

}

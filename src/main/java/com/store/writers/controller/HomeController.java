package com.store.writers.controller;

import com.store.writers.model.entity.Item;
import com.store.writers.model.entity.User;
import com.store.writers.service.ItemService;
import com.store.writers.service.UserService;
import com.store.writers.service.impl.UserDetailsServiceImpl;
import com.store.writers.utils.EmailService;
import com.store.writers.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userSecurityService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}

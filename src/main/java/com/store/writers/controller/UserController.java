package com.store.writers.controller;


import com.store.writers.model.Role;
import com.store.writers.model.entity.User;
import com.store.writers.service.SecurityService;
import com.store.writers.service.UserService;
import com.store.writers.utils.EmailService;
import com.store.writers.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Email;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EmailService emailService;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        //userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        //new users created throught the UI are always customers
        userForm.setRole(Role.CUSTOMER);
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        String message = "Welcome to [ink] Writer Store";
        emailService.sendSimpleMessage(userForm.getEmail(), "Welcome to [Ink] Store", message);
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }


    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        return "profiles/index";
    }

    @PostMapping("/updateUserInfo")
    public String updateUserInfo()
    {
        return "profile";
    }
}

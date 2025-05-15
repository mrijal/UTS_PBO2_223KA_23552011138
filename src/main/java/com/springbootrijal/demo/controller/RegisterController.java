package com.springbootrijal.demo.controller;

import com.springbootrijal.demo.model.User;
import com.springbootrijal.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            
            if (user.getUsername() == null || user.getUsername().isBlank()) {
                throw new IllegalArgumentException("Username tidak boleh kosong.");
            }
            if (user.getPassword() == null || user.getPassword().length() < 4) {
                throw new IllegalArgumentException("Password minimal 4 karakter.");
            }

            userService.registerUser(user);
            return "redirect:/login?registered";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}

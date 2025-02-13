package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class HomeController {
    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String home() {
        return "login";
    }

    @GetMapping(value = "/login")
    public String home1() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String userPage(Model model, Principal principal) {
        Person user = userService.getUserByUsername(principal.getName());  // Получаем пользователя по имени
        model.addAttribute("user", user);

        return "user";
    }
}

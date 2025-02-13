package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String usersList(Model model , Principal principal) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("roles1", userService.getRoles());
        model.addAttribute("user", new Person());
        System.out.println(principal);
        model.addAttribute("princ" , principal);
        return "admin";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }


    @GetMapping(value = "/new")
    public String newUser(Model model, Principal principal) {
        model.addAttribute("user", new Person());
        model.addAttribute("princ", principal);

        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") Person user) {
        System.out.println(user.toString());
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/update")
    public String updateGet(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/update";
    }

    @PostMapping(value = "/update")
    public String updatePost(@ModelAttribute("user") Person user) {
        user.setRoles(user.getRoles());
        System.out.println(user);
        userService.update(user);
        return "redirect:/admin";
    }

}

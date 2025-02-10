package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;

@Controller
@RequestMapping(value = "/admin")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String usersList(Model model){
        model.addAttribute("users",userService.listUsers());
        return "admin";
    }
    @GetMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") int id){
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model,Model model1){
        model.addAttribute("user",new Person());
        model1.addAttribute("roles1",userService.getRoles());

        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") Person user){
        System.out.println(user.toString());
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/update")
    public String updateGet(@RequestParam(value = "id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "/update";
    }
    @PostMapping(value = "/update")
    public String updatePost(@ModelAttribute("user") Person user){
        userService.update(user);
        return "redirect:/admin";
    }

}

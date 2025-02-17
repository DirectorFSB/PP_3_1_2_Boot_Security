package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/user/current")
    public ResponseEntity<Person> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(userService.getUserByUsername(principal.getName()));
    }
}

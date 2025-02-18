package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.service.PersonService;

import java.security.Principal;

@RestController
public class UserController {
    private PersonService personService;

    @Autowired
    public UserController(PersonService personService) {
        this.personService = personService;
    }



    @GetMapping("/user/current")
    public ResponseEntity<Person> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(personService.getUserByUsername(principal.getName()));
    }
}

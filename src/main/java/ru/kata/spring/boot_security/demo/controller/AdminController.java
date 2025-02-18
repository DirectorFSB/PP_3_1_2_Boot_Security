package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.PersonService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private PersonService personService;

    private RoleService roleService;

    @Autowired
    public AdminController(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<Role>> delete() {
        return ResponseEntity.ok(roleService.getRoles());
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Person user) {
        personService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Void> updateGet(@RequestBody Person person) {
        personService.update(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/users")
    public List<Person> usersList() {
        return personService.listUsers();
    }

    @GetMapping("/api/user")
    public Person userById(@RequestParam(value = "id") int id) {
        return personService.getUserById(id);
    }


}

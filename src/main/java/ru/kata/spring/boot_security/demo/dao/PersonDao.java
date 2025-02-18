package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface PersonDao {

    void add(Person user);

    List<Person> listUsers();

    void update(Person user);

    void delete(int id);

    Person getUserById(int id);

    Person getUserByUsername(String username);



}

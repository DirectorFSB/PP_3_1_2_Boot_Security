package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.PersonDao;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PersonServiceImp implements PersonService {

    PersonDao personDao;

    @Autowired
    public PersonServiceImp(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void add(Person user) {
        personDao.add(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> listUsers() {
        return personDao.listUsers();
    }

    @Transactional
    @Override
    public void update(Person user) {
        personDao.update(user);
    }

    @Override
    public void delete(int id) {
        personDao.delete(id);
    }

    @Transactional(readOnly = true)
    public Person getUserById(int id) {
        return personDao.getUserById(id);
    }

    @Override
    public Person getUserByUsername(String username) {
        return personDao.getUserByUsername(username);
    }


}

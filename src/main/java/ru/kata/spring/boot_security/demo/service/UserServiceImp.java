package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService{

    UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(Person user) {
        userDao.add(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public void update(Person user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
    @Transactional(readOnly = true)
    public Person getUserById(int id){
        return userDao.getUserById(id);
    }

    @Override
    public Person getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public Set<Role> getRoles() {
        return userDao.getRoles();
    }

    @Override
    public Role getRoleById(String id) {
        return userDao.getRoleById(id);
    }
}

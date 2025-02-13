package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import org.springframework.security.core.userdetails.User;

import java.util.function.Function;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao dao;
    private PasswordEncoder pswdEncoder;

    public UserDetailsServiceImpl() {

    }

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao, PasswordEncoder pswdEncoder) {
        this.dao = userDao;
        this.pswdEncoder = pswdEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = dao.getUserByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("Unknown user :" + username);
        }
        UserDetails userDetails = User.builder()
                .username(person.getUsername())
                .password(pswdEncoder.encode(person.getPassword()))
                .authorities(person.getRoles())
                .build();
        return userDetails;
    }
}

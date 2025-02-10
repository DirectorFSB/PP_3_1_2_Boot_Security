package ru.kata.spring.boot_security.demo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class StringToRoleConverter implements Converter<String, Role> {

    @Autowired
    private UserService userService;

    @Override
    public Role convert(String id) {
        return userService.getRoleById(id);
    }
}

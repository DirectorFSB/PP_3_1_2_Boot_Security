package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.PersonDao;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;
@Service
@Transactional
public class RoleServiceImp implements RoleService {

    RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public Role getRoleById(String id) {
        return roleDao.getRoleById(id);
    }
}

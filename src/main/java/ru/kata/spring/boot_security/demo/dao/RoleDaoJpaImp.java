package ru.kata.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoJpaImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRoles() {

        Set<Role> asd = new HashSet<>();
        asd.addAll(entityManager.createQuery("select r from Role r").getResultList());

        return asd;
    }

    @Override
    public Role getRoleById(String id) {

        List asd = entityManager.createQuery("select r from Role r where id = :id").setParameter("id", Integer.valueOf(id)).getResultList();
        return (Role) asd.get(0);
    }
}

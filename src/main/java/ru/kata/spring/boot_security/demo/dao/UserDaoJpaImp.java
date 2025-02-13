package ru.kata.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoJpaImp implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(Person user) {

        entityManager.persist(user);
    }

    @Override

    public List<Person> listUsers() {
        return entityManager.createQuery("select u from Person u ", Person.class).getResultList();
    }

    @Override
    public void update(Person user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        String query = "delete from Person where id = :id";
        entityManager.createQuery(query).setParameter("id", id).executeUpdate();
    }

    @Override
    public Person getUserById(int id) {
        return (Person) entityManager.createQuery("select u from Person u where id = :id").setParameter("id", id).getSingleResult();
    }

    @Override
    public Person getUserByUsername(String username) {

        return (Person) entityManager.createQuery("select u from Person u where userName = :username", Person.class).setParameter("username", username).getSingleResult();
    }

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

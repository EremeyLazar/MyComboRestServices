package ru.kata.spring.boot_security.demo.UsersDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save (User user) {
        entityManager.persist(user);
    }



}



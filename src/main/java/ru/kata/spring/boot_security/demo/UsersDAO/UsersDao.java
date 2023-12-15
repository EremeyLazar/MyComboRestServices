package ru.kata.spring.boot_security.demo.UsersDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save (User user) {
        entityManager.persist(user);
    }


    public List<User> getAll() {
        List<User> resultList = entityManager.createQuery("select u from User u", User.class).getResultList();
        return resultList;
    }

    public void createUser(User user) {
        entityManager.persist(user);
    }
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    public void update(User updatedUser, int id) {
        User needsUpdate = entityManager.find(User.class, id);
        needsUpdate.setUsername(updatedUser.getUsername());
        needsUpdate.setPassword(updatedUser.getPassword());
        needsUpdate.setYob(updatedUser.getYob());
        needsUpdate.setCountry(updatedUser.getCountry());
        needsUpdate.setRole(updatedUser.getRole());
        entityManager.persist(needsUpdate);
    }

    public User getOne(int id) {
        return entityManager.find(User.class, id);
    }
}



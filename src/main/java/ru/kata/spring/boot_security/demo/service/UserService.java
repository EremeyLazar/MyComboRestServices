package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.UsersDAO.UsersDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserService {
    // id, name, password, yob, country, roll


    @Autowired
    private UsersDao usersDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void createTheUser() {
        User user = new User("Anthonio", "23432", 2000, "Russia");
        usersDao.save(user);
    }

    public List<User> getAll() {
        List<User> resultList = usersDao.getAll();
        return resultList;
    }

    @Transactional
    public void createUser(User user) {
        String passwordCoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordCoded);
        user.setRole("ROLE_USER");
        usersDao.createUser(user);
    }

    @Transactional
    public void createAdmin () {
        User admin = new User("admin", "admin", 2000, "admin");
        String passwordCoded = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(passwordCoded);
        admin.setRole("ROLE_ADMIN");
        usersDao.createUser(admin);
    }

    @Transactional
    public void deleteUser(int id) {
        usersDao.deleteUser(id);
    }

    @Transactional
    public void update(User updatedUser, int id) {
        usersDao.update(updatedUser, id);
    }

    public User getOne(int id) {
        return usersDao.getOne(id);
    }
}

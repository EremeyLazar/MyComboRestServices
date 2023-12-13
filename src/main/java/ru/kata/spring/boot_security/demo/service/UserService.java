package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.UsersDAO.UsersDao;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public class UserService {
    // id, name, password, yob, country, roll


    @Autowired
    private UsersDao usersDao;


    @Transactional
    public void createUser() {
        User user = new User("Anthonio", "23432", 2000, "Russia");
        usersDao.save(user);
    }
}

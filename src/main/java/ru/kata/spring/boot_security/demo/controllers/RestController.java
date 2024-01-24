package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.exception_handling.UserIncorrectData;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AuthService;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private AuthService authService;

    @GetMapping("/users")
    public List <User> showAllUsers () {
        return authService.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUser (@PathVariable Integer id) {
        User user = authService.getOne(id);
        if (user == null) {
            throw new NoSuchUserException("there is no USER with ID = " + id + " found in DB");
        }
        return user;
    }

    @PostMapping("/users")
    public User postNewUser (@RequestBody User user) {
        authService.createUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser (@RequestBody User user) {
        authService.createUser(user);
        return user;
    }

}

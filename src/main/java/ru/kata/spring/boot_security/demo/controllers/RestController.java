package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return authService.getOne(id);
    }


}

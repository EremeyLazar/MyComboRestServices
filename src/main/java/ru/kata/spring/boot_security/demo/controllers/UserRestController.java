package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@RestController
@RequestMapping("/apiUser")
public class UserRestController {

    @Autowired
    private UserService service;

    @GetMapping("/getCurrentUser")
    public ResponseEntity<User> getCurrentUser() {
        User user = service.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = service.getOne(id);
        return ResponseEntity.ok(user);
    }
}

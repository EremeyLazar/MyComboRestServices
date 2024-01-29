package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AuthService;

import java.security.Principal;
import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/apiAuth")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @GetMapping("/getCurrentUser")
    public ResponseEntity<User> getCurrentUser() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity <List <User>> showAllUsers () {
        List <User> userList = authService.getAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/users/{id}")
    public User getUser (@PathVariable Integer id) {
        return authService.getOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity<String> postNewUser (@RequestBody User user) {
        authService.createUser(user);
        return ResponseEntity.ok("Done successfully with ID = " + user.getId());
    }

    @PutMapping("/users")
    public User updateUser (@RequestBody User user) {
        authService.createUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable Integer id) {
        authService.deleteUser(id);
        return ResponseEntity.ok("The user with id = " + id + " was deleted");
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById (@PathVariable Integer id) {
        User user = authService.getOne(id);
        return ResponseEntity.ok(user);
    }

}

package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/apiGame")
public class GameController {

    UserService userService = new UserService();


    @GetMapping("/getNum/{id}")
    public ResponseEntity<Integer> getUserById(@PathVariable int id) {
        userService.init(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/say")
    public ResponseEntity <List <String>> sayWord() {
        List allWords = userService.passWord();
        return ResponseEntity.ok(allWords);
    }


}

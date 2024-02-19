package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.function_service.GameFunctionService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/apiGame")
public class GameController {

    GameFunctionService gameFunctionService = new GameFunctionService();


    @GetMapping("/getNum/{id}")
    public ResponseEntity<Integer> getUserById(@PathVariable int id) {
        gameFunctionService.init(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/say")
    public ResponseEntity <List <String>> sayWord() {
        List allWords = gameFunctionService.passWord();
        return ResponseEntity.ok(allWords);
    }


}

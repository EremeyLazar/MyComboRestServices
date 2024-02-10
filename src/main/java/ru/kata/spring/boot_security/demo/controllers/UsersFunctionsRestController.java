package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Game;
import ru.kata.spring.boot_security.demo.model.TopWords;
import ru.kata.spring.boot_security.demo.service.UsersFunctions;

import java.util.Map;


@RestController
@RequestMapping("/apiFunc")
public class UsersFunctionsRestController {

    private int number;

    @PostMapping("/processWords")
    public ResponseEntity<Map<String, Integer>> processWords(@RequestBody TopWords topWords) {
        UsersFunctions funcService = new UsersFunctions();
        Map<String, Integer> result = funcService.processWords(topWords.getWord(), topWords.getLimit());
        return ResponseEntity.ok(result);
    }
}

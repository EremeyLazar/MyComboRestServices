package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.function_service.WordsFunctionsService;
import ru.kata.spring.boot_security.demo.model.TopWords;

import java.util.Map;


@RestController
@RequestMapping("/apiFunc")
public class UsersFunctionsRestController {

    @PostMapping("/processWords")
    public ResponseEntity<Map<String, Integer>> processWords(@RequestBody TopWords topWords) {
        WordsFunctionsService wordsFunctionsService = new WordsFunctionsService();
        Map<String, Integer> result = wordsFunctionsService.processWords(topWords.getWord(), topWords.getLimit());
        return ResponseEntity.ok(result);
    }
}

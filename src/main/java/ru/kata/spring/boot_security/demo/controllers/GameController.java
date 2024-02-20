package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.function_service.GameFunctionService;

import java.util.List;


@RestController
@RequestMapping("/apiGame")
public class GameController {

    @Autowired
    private GameFunctionService gameFunctionService;
//    GameService gameService = new GameService();


    @GetMapping("/getNum/{id}")
    public ResponseEntity<Integer> getUserById(@PathVariable int id) {
        gameFunctionService.init(id);
//        gameService.save(new User("savetest", "test", 2000, "test"));
        return ResponseEntity.ok(id);
    }

    @GetMapping("/say")
    public ResponseEntity <List <String>> sayWord() {
        List allWords = gameFunctionService.passWord();
        return ResponseEntity.ok(allWords);
    }


}

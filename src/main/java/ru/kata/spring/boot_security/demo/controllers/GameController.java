package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.TopWords;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.FunctionsService;
import ru.kata.spring.boot_security.demo.service.GameService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/apiGame")
public class GameController {

    GameService service = new GameService();
    UserService userService = new UserService();

    @GetMapping("/getNum/{id}")
    public ResponseEntity<Byte> getUserById(@PathVariable byte id) {
        System.out.println("КОНТРОЛЛЕР ПОЛУЧИЛ " + id);
        service.init(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/say")
    public ResponseEntity <List<String>> sayWord () {
        User user = userService.getCurrentUser();
        List<String> word = user.getGame().getMessages().stream().limit(5).collect(Collectors.toList());
        System.out.println(word);
        return ResponseEntity.ok(word);
    }
}

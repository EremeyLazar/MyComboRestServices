package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HeyController {

    @Autowired
    private UserService service;

    @GetMapping("/hey")
    public String sayHey () {
        service.createTheUser();
        return "hey";
    }

    @GetMapping("/user")
    public String user () {
        return "user";
    }

    @GetMapping("/index")
    public String index () {
        return "index";
    }

    @GetMapping("/admin")
    public String admin (Model model) {
        List<User> resultList = service.getAll();
        model.addAttribute("userlist", resultList);
        return "admin";
    }

    //NEW USER!!!
    @GetMapping(value = "/newuser")
    public String newUser(Model model) {
        model.addAttribute("newuser", new User());
        return "usercreation";
    }

    @PostMapping(value = "/newuser")
    public String createUser(@ModelAttribute("newuser") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/newuser";
        }
        service.createUser(user);
        return "redirect:admin";
    }

}

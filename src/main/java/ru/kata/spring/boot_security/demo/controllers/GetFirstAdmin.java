package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!     FOR FIRST ADMIN - http://localhost:8080/getadmin     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
@Controller
public class GetFirstAdmin {

    @Autowired
    private UserService service;

    @GetMapping("/getadmin")
    public String getAdmin() {
        service.createTheAdmin();
        return "getadmin";
    }
}

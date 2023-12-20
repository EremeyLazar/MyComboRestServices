package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserService service;

    @GetMapping("/getuser")
    public String sayHey() {
        service.createTheUser();
        return "hey";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("sayname", principal.getName());
        return "user";
    }

    @GetMapping("/readonly")
    public String readonly(Model model, Principal principal) {
        model.addAttribute("saynamereadonly", principal.getName());
        return "readonly";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("sayhitoadmin", principal.getName());
        List<User> resultList = service.getAll();
        model.addAttribute("userlist", resultList);
        return "admin";
    }

    @GetMapping("/getadmin")
    public String getAdmin() {
        service.createAdmin();
        return "getadmin";
    }

    //NEW USER Landing Page!!!
    @GetMapping(value = "/index")
    public String newUser(Model model) {
        model.addAttribute("userreg", new User());
        return "index";
    }

    @PostMapping(value = "/index")
    public String createUser(@ModelAttribute("userreg") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/index";
        }
        service.createUser(user);
        return "redirect:user";
    }


    //    DELETE USER!!!
    @GetMapping(value = "/deleteuser")
    public String deleteUser(@RequestParam("id") int id) {
        service.deleteUser(id);
        return "redirect:deleted";
    }

    @GetMapping(value = "/deleted")
    public String deleted(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Requested user has been removed!!!");
        model.addAttribute("messages", messages);
        return "deleted";
    }

    //    UPDATE USER!!!
    @GetMapping(value = "/update")
    public String updateUser(ModelMap model, @RequestParam("id") int id) {
        model.addAttribute("upuser", service.getOne(id));
        return "update";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("upuser") @Valid User updatedUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/update";
        }
        service.update(updatedUser, updatedUser.getId());
        return "redirect:/admin";
    }

}

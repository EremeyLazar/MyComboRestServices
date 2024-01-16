package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AuthService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public String adminPanelPage(Model model, Principal principal) {
        User user = authService.getCurrentUser();
        Set<Role> userRoles = user.getRoles();
        model.addAttribute("nameWelcomeLine", user.getUsername());
        model.addAttribute("roleWelcomeLine", userRoles);

        model.addAttribute("user", authService.findByUsername(principal.getName())); //?

        List<User> resultList = authService.getAll();
        model.addAttribute("userList", resultList);
        model.addAttribute("userToCreate", new User());
        model.addAttribute("allRoles", authService.getAllRoles());
        return "admin-panel";
    }

    @PostMapping(value = "")
    public String createUser(@ModelAttribute("userToCreate") @Valid User user) {
        authService.createUser(user);
        return "redirect:/admin";
    }

//    @GetMapping(value = "/delete")
//    public String deleteUser(ModelMap model, @RequestParam("id") int id) {
//        model.addAttribute("deluser", authService.getOne(id));
//        return "delete";
//    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int userId) {
        authService.deleteUser(userId);
        return "redirect:/admin";
    }
//
//    @GetMapping(value = "/update")
//    public String updateUser(ModelMap model, @RequestParam("id") int id) {
//        model.addAttribute("editUser", authService.getOne(id));
//        return "admin-panel";
//    }
//
//    @PostMapping(value = "/update")
//    public String updateUser(@ModelAttribute("editUser") @Valid User updatedUser) {
//        authService.update(updatedUser, updatedUser.getId());
//        return "redirect:/admin";
//    }


    @PatchMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        authService.update(user, id);
        return "redirect:/admin";
    }

}

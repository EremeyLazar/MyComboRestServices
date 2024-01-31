//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.AuthService;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/ad")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @GetMapping("")
//    public String adminPanelPage(Model model) {
//        model.addAttribute("user", authService.getCurrentUser());
//        model.addAttribute("userList", authService.getAll());
//        model.addAttribute("userToCreate", new User());
//        model.addAttribute("allRoles", authService.getAllRoles());
//        return "admin-panel";
//    }
//
//    @PostMapping(value = "")
//    public String createUser(@ModelAttribute("userToCreate") @Valid User user) {
//        authService.createUser(user);
//        return "redirect:/admin-panel";
//    }
//
//    @PostMapping("/delete")
//    public String deleteUser(@RequestParam("id") Integer userId) {
//        authService.deleteUser(userId);
//        return "redirect:/admin-panel";
//    }
//
//    @GetMapping(value = "/update")
//    public String updateUser(ModelMap model, @RequestParam("id") Integer id) {
//        model.addAttribute("editUser", authService.getOne(id));
//        return "edit";
//    }
//
//    @PostMapping(value = "/update")
//    public String updateUser(@ModelAttribute("editUser") @Valid User updatedUser) {
//        authService.update(updatedUser, updatedUser.getId());
//        return "redirect:/admin-panel";
//    }
//
//}

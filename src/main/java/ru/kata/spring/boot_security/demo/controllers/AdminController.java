package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;

    @GetMapping("")
    public String admin(Model model) {
        User user = service.getCurrentUser();
        Set<Role> userRoles = user.getRoles();
        model.addAttribute("sayhitoadmin", user.getUsername());
        model.addAttribute("userRole", userRoles);

        List<User> resultList = service.getAll();
        model.addAttribute("userlist", resultList);

        model.addAttribute("userreg", new User());
        model.addAttribute("allRoles", service.getAllRoles());

        return "admin";
    }

    @PostMapping(value = "/newuser")
    public String createUser(@ModelAttribute("userreg") @Valid User user,
                             BindingResult bindingResult,
                             @RequestParam(value = "selectedRoles", required = false) List<Integer> selectedRoleIds) {
        if (service.isUserExists(user.getUsername())) {
            bindingResult.rejectValue("username", "error.user", "User with that name already exists!");
            return "admin";
        } else if (bindingResult.hasErrors()) {
            return "admin";
        }

        service.createUser(user, selectedRoleIds);

        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String delete(ModelMap model, @RequestParam("id") int id) {
        model.addAttribute("deluser", service.getOne(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int userId) {
        service.deleteUser(userId);
        return "redirect:/admin";
    }

    @GetMapping(value = "/update")
    public String updateUser(ModelMap model, @RequestParam("id") int id) {
        model.addAttribute("upuser", service.getOne(id));
        return "edit";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("upuser") @Valid User updatedUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/edit";
        }
        service.update(updatedUser, updatedUser.getId());
        return "redirect:/admin";
    }

}

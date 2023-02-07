package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Controller
public class SecurityController {
    private final UserService userService;
    public SecurityController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(@ModelAttribute("user") User user,Model model) {
        List<Role> listRoles=userService.findRoles();
        model.addAttribute(model.addAttribute("listRoles", listRoles));
        return "addUser";
    }

    @PostMapping(value = "/admin/new")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        User user=userService.findUserById(id);
        List<Role> listRoles=userService.findRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "/updateUser";
    }
    @PatchMapping("/admin/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

}


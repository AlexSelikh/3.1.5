package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;


@Controller
public class SecurityController {

    private final UserServiceInterface userServiceInterface;

    public SecurityController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> users = userServiceInterface.allUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        List<Role> listRoles = userServiceInterface.findRoles();
        model.addAttribute("listRoles", listRoles);
        return "addUser";
    }

    @PostMapping(value = "/admin/new")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceInterface.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userServiceInterface.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        User user = userServiceInterface.findUserById(id);
        List<Role> listRoles = userServiceInterface.findRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "/updateUser";
    }

    @PatchMapping("/admin/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userServiceInterface.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String findUser(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
}


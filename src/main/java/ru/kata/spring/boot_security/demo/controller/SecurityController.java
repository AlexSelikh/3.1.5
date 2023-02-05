package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class SecurityController {
    private UserService userService;
    public SecurityController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/" )
    public String forAllUsers(){
        return "index";
    }
    @GetMapping("/user")
    public String showAllUsers(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "allusers";
    }
}

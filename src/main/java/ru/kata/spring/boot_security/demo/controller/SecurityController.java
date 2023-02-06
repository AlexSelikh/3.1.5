package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Controller
public class SecurityController {
    private UserService userService;
    public SecurityController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/admin")
    public String userList(Model model) {
        List<User> users=userService.allUsers();
        model.addAttribute("allUsers", users);
        return "allusers";
    }
    @GetMapping("/" )
    public String forAllUsers(){
        return "index";
    }
   /* @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "allusers";
    }*/
}

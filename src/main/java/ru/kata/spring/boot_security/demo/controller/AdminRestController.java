package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserServiceInterface userServiceInterface;

    public AdminRestController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
       List<Role> response= userServiceInterface.findRoles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
       List<User> response= userServiceInterface.allUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User>getById(@PathVariable("id") Integer id) {
        User response = userServiceInterface.findUserById(id);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<HttpStatus> newUser(@RequestBody User user) {
        userServiceInterface.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user,@PathVariable("id") Integer id) {
        userServiceInterface.updateUser(id,user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id) {
        userServiceInterface.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/api/user")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = userServiceInterface.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
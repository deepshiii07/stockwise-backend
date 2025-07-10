package com.stockwise.backend.controller;
import com.stockwise.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.stockwise.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    
    //login an existing user
    @PostMapping("/login") 
    public User loginUser(@RequestBody User loginData) {
        return userService.loginUser(loginData.getEmail(), loginData.getPassword());
    }
}

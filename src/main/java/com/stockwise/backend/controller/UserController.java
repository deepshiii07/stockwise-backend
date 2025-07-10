package com.stockwise.backend.controller;

import com.stockwise.backend.model.User;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.stockwise.backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.stockwise.backend.dto.UserResponseDTO;

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
    public UserResponseDTO loginUser(@RequestBody User loginData) {
        return userService.loginUser(loginData.getEmail(), loginData.getPassword());
    }
    
    //get user by id
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    //get all users 
    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }


}

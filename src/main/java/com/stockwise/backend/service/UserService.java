package com.stockwise.backend.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stockwise.backend.model.User;
import com.stockwise.backend.repository.UserRepository;
import com.stockwise.backend.dto.UserResponseDTO;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public User registerUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
		    throw new RuntimeException("Email already registered");
		}
		return userRepository.save(user);
	}
	
	//login method
	public UserResponseDTO loginUser(String email, String password) {
	    User user = userRepository.findByEmail(email)
	        .orElseThrow(() -> new RuntimeException("Invalid email"));

	    if (!user.getPassword().equals(password)) {
	        throw new RuntimeException("Incorrect password");
	    }

	    return new UserResponseDTO(
	        user.getId(),
	        user.getName(),
	        user.getEmail(),
	        user.getRole()
	    );
	}
	
	//get user by ID
	public UserResponseDTO getUserById(Long id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	    
	    return new UserResponseDTO(
	            user.getId(),
	            user.getName(),
	            user.getEmail(),
	            user.getRole()
	    );
	}
	
	//get all users
	public List<UserResponseDTO> getAllUsers() {
	    List<User> users = userRepository.findAll();

	    return users.stream()
	            .map(user -> new UserResponseDTO(
	                    user.getId(),
	                    user.getName(),
	                    user.getEmail(),
	                    user.getRole()
	            ))
	            .toList();
	}




}

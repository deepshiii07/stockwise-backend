package com.stockwise.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stockwise.backend.model.User;
import com.stockwise.backend.repository.UserRepository;

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
	public User loginUser(String email, String password) {
	    User user = userRepository.findByEmail(email)
	        .orElseThrow(() -> new RuntimeException("Invalid email"));

	    if (!user.getPassword().equals(password)) {
	        throw new RuntimeException("Incorrect password");
	    }

	    return user;
	}

}

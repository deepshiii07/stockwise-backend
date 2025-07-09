package com.stockwise.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.stockwise.backend.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
	 // Custom method to find user by email (used in login)
    Optional<User> findByEmail(String email);

    // Optional: check if email already exists
    boolean existsByEmail(String email);
}

package com.stockwise.backend.dto;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;

    public UserResponseDTO(Long id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters only (no setters needed unless you want to)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}

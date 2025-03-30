package com.example.travelapp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user in the travel application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id; // Unique identifier for the user
    private String name; // User's name
    private String email; // User's email address
    private Set<String> following = new HashSet<>(); // Set of user IDs this user is following
    private Set<String> followers = new HashSet<>(); // Set of user IDs following this user
}

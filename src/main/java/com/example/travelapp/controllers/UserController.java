package com.example.travelapp.controllers;

import com.example.travelapp.models.User;
import com.example.travelapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Endpoints for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */

    @GetMapping("/")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html";
    }

    @Operation(summary = "Retrieve all users", description = "Fetches all users from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users")
    })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Creates a new user.
     *
     * @param user The user object to be created.
     * @return The created user.
     */
    @Operation(summary = "Create a new user", description = "Adds a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Allows a user to follow another user.
     *
     * @param userId   The ID of the user who wants to follow.
     * @param followId The ID of the user to be followed.
     * @return The updated user object.
     */
    @Operation(summary = "Follow a user", description = "Allows a user to follow another user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully followed the user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/{userId}/follow/{followId}")
    public ResponseEntity<?> followUser(@PathVariable String userId, @PathVariable String followId) {
        Optional<User> updatedUser = userService.followUser(userId, followId);
        if (updatedUser.isPresent()) {
            return ResponseEntity.ok(updatedUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or follow operation failed.");
        }
    }
    /**
     * Allows a user to unfollow another user.
     *
     * @param userId     The ID of the user who wants to unfollow.
     * @param unfollowId The ID of the user to be unfollowed.
     * @return ResponseEntity containing the updated user information.
            */
    @PostMapping("/{userId}/unfollow/{unfollowId}")
    public ResponseEntity<User> unfollowUser(@PathVariable String userId, @PathVariable String unfollowId) {
        Optional<User> updatedUser = userService.unfollowUser(userId, unfollowId);
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}




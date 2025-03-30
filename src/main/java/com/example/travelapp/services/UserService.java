package com.example.travelapp.services;

import com.example.travelapp.models.User;
import com.example.travelapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Follow a user
    public Optional<User> followUser(String userId, String followId) {
        if (userId.equals(followId)) {
            throw new IllegalArgumentException("Users cannot follow themselves.");
        }

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> followOpt = userRepository.findById(followId);

        if (userOpt.isPresent() && followOpt.isPresent()) {
            User user = userOpt.get();
            User follow = followOpt.get();

            Set<String> following = user.getFollowing();
            Set<String> followers = follow.getFollowers();

            if (following.add(followId) && followers.add(userId)) {
                userRepository.save(user);
                userRepository.save(follow);
            }

            return Optional.of(user);
        }

        return Optional.empty();
    }

    // Unfollow a user
    public Optional<User> unfollowUser(String userId, String unfollowId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<User> unfollowOpt = userRepository.findById(unfollowId);

        if (userOpt.isPresent() && unfollowOpt.isPresent()) {
            User user = userOpt.get();
            User unfollow = unfollowOpt.get();

            Set<String> following = user.getFollowing();
            Set<String> followers = unfollow.getFollowers();

            if (following.remove(unfollowId) && followers.remove(userId)) {
                userRepository.save(user);
                userRepository.save(unfollow);
            }

            return Optional.of(user);
        }

        return Optional.empty();
    }

    // Get the number of followers for a user
    public int getFollowerCount(String userId) {
        return userRepository.findById(userId)
                .map(user -> user.getFollowers().size())
                .orElse(0);
    }

    // Get the number of users a user is following
    public int getFollowingCount(String userId) {
        return userRepository.findById(userId)
                .map(user -> user.getFollowing().size())
                .orElse(0);
    }
}

package com.example.travelapp.repositories;

import com.example.travelapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
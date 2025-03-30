package com.example.travelapp.repositories;

import com.example.travelapp.models.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TripRepository extends MongoRepository<Trip, String> {
    List<Trip> findByUserId(String userId);
    long countByUserId(String userId);
}

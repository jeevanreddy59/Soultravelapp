package com.example.travelapp.services;

import com.example.travelapp.models.Trip;
import com.example.travelapp.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    // Retrieve trips by user ID
    public List<Trip> getTripsByUserId(String userId) {
        return tripRepository.findByUserId(userId);
    }

    // Create a new trip
    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    // Get the number of trips (posts) a user has made
    public int getTripCountByUserId(String userId) {
        return tripRepository.findByUserId(userId).size();
    }
}

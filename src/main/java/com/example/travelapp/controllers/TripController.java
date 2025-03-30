package com.example.travelapp.controllers;

import com.example.travelapp.models.Trip;
import com.example.travelapp.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;


    @GetMapping("/user/{userId}")
    public List<Trip> getTripsByUserId(@PathVariable String userId) {
        return tripService.getTripsByUserId(userId);
    }

    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.createTrip(trip);
    }

    @GetMapping("/user/{userId}/count")
    public long getTripCountByUserId(@PathVariable String userId) {
        return tripService.getTripCountByUserId(userId);
    }

}

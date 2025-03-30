package com.example.travelapp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a trip created by a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "trips")
public class Trip {
    @Id
    private String id; // Unique identifier for the trip
    private String userId; // ID of the user who created the trip
    private String destination; // Destination of the trip
    private String description; // Description of the trip
}

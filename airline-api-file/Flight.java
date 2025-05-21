package com.example.airline_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String airportFrom;
    private String airportTo;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private int capacity;
    private int availableSeats;
    private int duration; // min
}

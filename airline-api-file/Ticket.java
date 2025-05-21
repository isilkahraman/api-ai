package com.example.airline_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNumber;

    @ManyToOne
    private Flight flight;

    @ManyToOne(cascade = CascadeType.ALL)
    private Passenger passenger;
}


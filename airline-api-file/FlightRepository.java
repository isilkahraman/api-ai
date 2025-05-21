package com.example.airline_api.repository;

import com.example.airline_api.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByAirportFromAndAirportToAndDateFromBetween(
            String airportFrom,
            String airportTo,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    Flight findByFlightNumber(String flightNumber);
}


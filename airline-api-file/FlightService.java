package com.example.airline_api.service;

import com.example.airline_api.entity.Flight;
import com.example.airline_api.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Flight addFlight(Flight flight) {
        flight.setAvailableSeats(flight.getCapacity());
        return flightRepository.save(flight);
    }

    public List<Flight> queryFlights(String airportFrom, String airportTo,
                                     LocalDateTime dateFrom, LocalDateTime dateTo,
                                     boolean sortByDate) {
        List<Flight> flights = flightRepository.findByAirportFromAndAirportToAndDateFromBetween(
                airportFrom, airportTo, dateFrom, dateTo
        );

        if (sortByDate) {
            flights.sort((a, b) -> a.getDateFrom().compareTo(b.getDateFrom()));
        }

        return flights;
    }

    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }
}


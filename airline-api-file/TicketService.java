package com.example.airline_api.service;

import com.example.airline_api.entity.Flight;
import com.example.airline_api.entity.Passenger;
import com.example.airline_api.entity.Ticket;
import com.example.airline_api.repository.FlightRepository;
import com.example.airline_api.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    public Ticket buyTicket(String flightNumber, String passengerName) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        if (flight == null || flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("Could not find flight with flight number " + flightNumber);
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);

        Passenger passenger = new Passenger();
        passenger.setName(passengerName);

        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);

        return ticketRepository.save(ticket);
    }
}


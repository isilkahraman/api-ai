package com.example.airline_api.controller;

import com.example.airline_api.entity.Ticket;
import com.example.airline_api.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public Ticket buyTicket(@RequestParam String flightNumber, @RequestParam String passengerName) {
        return ticketService.buyTicket(flightNumber, passengerName);
    }
}


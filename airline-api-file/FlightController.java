package com.example.airline_api.controller;

import com.example.airline_api.entity.Flight;
import com.example.airline_api.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @GetMapping
    public List<Flight> queryFlights(
            @RequestParam String airportFrom,
            @RequestParam String airportTo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo,
            @RequestParam(defaultValue = "false") boolean sortByDate) {

        return flightService.queryFlights(airportFrom, airportTo, dateFrom, dateTo, sortByDate);
    }
}


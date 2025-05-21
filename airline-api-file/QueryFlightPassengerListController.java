package com.example.airline_api.controller;

import com.example.airline_api.dto.PassengerSeatDTO;
import com.example.airline_api.service.QueryFlightPassengerListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/passenger-list")
@RequiredArgsConstructor
public class QueryFlightPassengerListController {

    private final QueryFlightPassengerListService queryService;

    @GetMapping
    public Page<PassengerSeatDTO> getPassengerList(
            @RequestParam String flightNumber,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return queryService.getPassengerList(flightNumber, date, PageRequest.of(page, size));
    }
}


package com.example.airline_api.controller;

import com.example.airline_api.entity.CheckIn;
import com.example.airline_api.service.CheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/checkin")
@RequiredArgsConstructor
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping
    public CheckIn assignSeat(@RequestParam Long ticketNumber, @RequestParam String seatNumber) {
        return checkInService.assignSeat(ticketNumber, seatNumber);
    }
}


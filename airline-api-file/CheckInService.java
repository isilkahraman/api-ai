package com.example.airline_api.service;

import com.example.airline_api.entity.CheckIn;
import com.example.airline_api.entity.Ticket;
import com.example.airline_api.repository.CheckInRepository;
import com.example.airline_api.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final TicketRepository ticketRepository;
    private final CheckInRepository checkInRepository;

    public CheckIn assignSeat(Long ticketNumber, String seatNumber) {
        Ticket ticket = ticketRepository.findById(ticketNumber)
                .orElseThrow(() -> new RuntimeException("Couldn't find ticket'"));

        if (checkInRepository.findByTicket_TicketNumber(ticketNumber).isPresent()) {
            throw new RuntimeException("Already assigned seat");
        }

        CheckIn checkIn = new CheckIn();
        checkIn.setTicket(ticket);
        checkIn.setSeatNumber(seatNumber);

        return checkInRepository.save(checkIn);
    }
}

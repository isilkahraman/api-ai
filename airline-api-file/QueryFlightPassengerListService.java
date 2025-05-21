package com.example.airline_api.service;

import com.example.airline_api.dto.PassengerSeatDTO;
import com.example.airline_api.entity.Ticket;
import com.example.airline_api.repository.CheckInRepository;
import com.example.airline_api.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryFlightPassengerListService {

    private final TicketRepository ticketRepository;
    private final CheckInRepository checkInRepository;

    public Page<PassengerSeatDTO> getPassengerList(String flightNumber, LocalDateTime date, Pageable pageable) {
        List<Ticket> tickets = ticketRepository.getTicketsByFlightAndDate(flightNumber, date);

        List<PassengerSeatDTO> passengerSeatList = tickets.stream().map(ticket -> {
            PassengerSeatDTO dto = new PassengerSeatDTO();
            dto.setPassengerName(ticket.getPassenger().getName());
            checkInRepository.findByTicket_TicketNumber(ticket.getTicketNumber()).ifPresentOrElse(
                    checkIn -> dto.setSeatNumber(checkIn.getSeatNumber()),
                    () -> dto.setSeatNumber("Not Checked In")
            );
            return dto;
        }).toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), passengerSeatList.size());
        List<PassengerSeatDTO> pageList = passengerSeatList.subList(start, end);

        return new PageImpl<>(pageList, pageable, passengerSeatList.size());
    }
}


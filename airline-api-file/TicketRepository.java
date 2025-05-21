package com.example.airline_api.repository;

import com.example.airline_api.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.flight.flightNumber = :flightNumber AND t.flight.dateFrom = :dateFrom")
    List<Ticket> getTicketsByFlightAndDate(@Param("flightNumber") String flightNumber, @Param("dateFrom") LocalDateTime dateFrom);


}


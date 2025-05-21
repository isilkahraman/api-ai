package com.example.airline_api.repository;

import com.example.airline_api.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    Optional<CheckIn> findByTicket_TicketNumber(Long ticketNumber);
}


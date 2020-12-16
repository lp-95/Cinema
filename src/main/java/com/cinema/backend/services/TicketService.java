package com.cinema.backend.services;

import com.cinema.backend.dto.TicketDto;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Ticket;

import java.util.UUID;

public interface TicketService {
    Ticket getById( UUID id ) throws NotFoundException;
    Ticket update( UUID id , TicketDto dto ) throws NotFoundException;
}

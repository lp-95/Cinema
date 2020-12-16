package com.cinema.backend.services;

import com.cinema.backend.dto.TicketDto;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Ticket;
import com.cinema.backend.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.cinema.backend.exceptions.ErrorMessages.*;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    public TicketServiceImpl( TicketRepository ticketRepository ) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket getById( UUID id ) throws NotFoundException {
        return this.ticketRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( RECORD_NOT_FOUND.getErrorMessage() ) );
    }

    @Override
    public Ticket update( UUID id, TicketDto dto ) throws NotFoundException {
        Ticket ticket = getById( id );
        ticket.setDiscount( dto.getDiscount() );
        return this.ticketRepository.save( ticket );
    }
}
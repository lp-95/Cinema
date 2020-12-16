package com.cinema.backend.controllers;

import com.cinema.backend.dto.TicketDto;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.services.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping( path = "/tickets" )
public class TicketController {

    @Autowired
    private final TicketServiceImpl service;

    public TicketController( TicketServiceImpl service ) {
        this.service = service;
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.PUT,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> update( @PathVariable UUID id, @RequestBody TicketDto dto ) {
        try {
            return new ResponseEntity<>( this.service.update( id, dto ), HttpStatus.OK );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

}

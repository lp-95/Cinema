package com.cinema.backend.controllers;

import com.cinema.backend.dto.HallDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.services.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping( path = "/halls" )
public class HallController {

    @Autowired
    private final HallServiceImpl hallService;

    public HallController( HallServiceImpl hallService ) {
        this.hallService = hallService;
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> getHallById( @PathVariable UUID id ) {
        try {
            return new ResponseEntity<>( this.hallService.getById( id ), HttpStatus.OK );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

    @RequestMapping( path = "",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> getHallsPage( @RequestParam( value = "page", defaultValue = "1" ) int page,
                                           @RequestParam( value = "size", defaultValue = "25" ) int size ) {
        return new ResponseEntity<>( this.hallService.getHalls( page, size), HttpStatus.OK );
    }

    @RequestMapping( path = "/{name}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> searchHalls( @PathVariable String name,
                                          @RequestParam( value = "page", defaultValue = "1" ) int page,
                                          @RequestParam( value = "size", defaultValue = "25" ) int size ) {
        return new ResponseEntity<>( this.hallService.search( page, size, name), HttpStatus.OK );
    }

    @RequestMapping( path = "",
            method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> saveNewHall( @RequestBody HallDto dto ) {
        try {
            return new ResponseEntity<>( this.hallService.save( dto ), HttpStatus.OK );
        } catch ( ConflictException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.CONFLICT );
        } catch ( BadRequestException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.PUT,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> updateHall( @PathVariable UUID id, @RequestBody HallDto dto ) {
        try {
            return new ResponseEntity<>( this.hallService.update( id, dto ), HttpStatus.OK );
        } catch ( BadRequestException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.DELETE,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> deleteHall( @PathVariable UUID id ) {
        try {
            this.hallService.delete( id );
            return ResponseEntity.ok().build();
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }
}

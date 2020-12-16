package com.cinema.backend.controllers;

import com.cinema.backend.dto.MovieDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping( path = "/movies" )
public class MovieController {

    @Autowired
    private final MovieServiceImpl movieService;

    public MovieController( MovieServiceImpl movieService ) {
        this.movieService = movieService;
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> getById( @PathVariable UUID id ) {
        try {
            return new ResponseEntity<>( this.movieService.getById( id ), HttpStatus.OK );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

    @RequestMapping( path = "",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> getMovies( @RequestParam( value = "page", defaultValue = "1" ) int page,
                                        @RequestParam( value = "size", defaultValue = "25" ) int size ) {
        return new ResponseEntity<>( this.movieService.getMovies( page, size), HttpStatus.OK );
    }

    @RequestMapping( path = "/{name}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> search( @PathVariable String name,
                                     @RequestParam( value = "page", defaultValue = "1" ) int page,
                                     @RequestParam( value = "size", defaultValue = "25" ) int size ) {
        return new ResponseEntity<>( this.movieService.search( page, size, name ), HttpStatus.OK );
    }

    @RequestMapping( path = "",
            method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> save( @RequestBody MovieDto dto ) {
        try {
            return new ResponseEntity<>( this.movieService.save( dto ), HttpStatus.OK );
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
    public ResponseEntity<?> update( @PathVariable UUID id, @RequestBody MovieDto dto ) {
        try {
            return new ResponseEntity<>( this.movieService.update( id, dto ), HttpStatus.OK );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        } catch ( BadRequestException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.DELETE )
    public ResponseEntity<?> delete( @PathVariable UUID id ) {
        try {
            this.movieService.delete( id );
            return ResponseEntity.ok().build();
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }
}

package com.cinema.backend.controllers;

import com.cinema.backend.dto.UserDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping( path = "/users" )
public class UserController {

    @Autowired
    private final UserServiceImpl userService;

    public UserController( UserServiceImpl userService ) {
        this.userService = userService;
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> getUserById( @PathVariable UUID id ) {
        try {
            return new  ResponseEntity<>( this.userService.findById( id ), HttpStatus.OK );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

    @RequestMapping( path = "",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> getUsersPage( @RequestParam( value = "page", defaultValue = "1" ) int page,
                                           @RequestParam( value = "size", defaultValue = "25" ) int size ) {
        return new ResponseEntity<>( this.userService.getUsers( page, size), HttpStatus.OK );
    }

    @RequestMapping( path = "/{firstName}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> searchUsers( @PathVariable String firstName,
                                          @RequestParam( value = "page", defaultValue = "1" ) int page,
                                          @RequestParam( value = "size", defaultValue = "25" ) int size ) {
        return new ResponseEntity<>( this.userService.search( page, size, firstName ), HttpStatus.OK );
    }

    @RequestMapping( path = "",
            method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> saveUser( @RequestBody UserDto dto ) {
        try {
            return new ResponseEntity<>( this.userService.save( dto ), HttpStatus.OK );
        } catch ( ConflictException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.CONFLICT );
        } catch ( BadRequestException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }
    @RequestMapping( path = "/{id}",
            method = RequestMethod.PUT,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE  },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> updateUser( @PathVariable UUID id, @RequestBody UserDto dto ) {
        try {
            return new ResponseEntity<>( this.userService.update( id, dto ), HttpStatus.OK );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        } catch ( BadRequestException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.DELETE )
    public ResponseEntity<?> deleteUser( @PathVariable UUID id ) {
        try{
            this.userService.delete( id );
            return  ResponseEntity.ok().build();
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }
}

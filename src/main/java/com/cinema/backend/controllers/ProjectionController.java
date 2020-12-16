package com.cinema.backend.controllers;

import com.cinema.backend.dto.ProjectionDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.services.ProjectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping( path = "/projections" )
public class ProjectionController {

    @Autowired
    private final ProjectionServiceImpl projectionService;

    public ProjectionController( ProjectionServiceImpl projectionService ) {
        this.projectionService = projectionService;
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> getProjectionById( @PathVariable UUID id ) {
        try {
            return new ResponseEntity<>( this.projectionService.getById( id ), HttpStatus.OK );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

    @RequestMapping( path = "",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> getProjectionsPage( @RequestParam( value = "page", defaultValue = "1" ) int page,
                                                 @RequestParam( value = "size", defaultValue = "25" ) int size ) {
        return new ResponseEntity<>( this.projectionService.getProjections( page, size), HttpStatus.OK );
    }

    @RequestMapping( path = "",
            method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> saveNewProjection( @RequestBody ProjectionDto dto ) {
        try {
            return new ResponseEntity<>( this.projectionService.save( dto ), HttpStatus.OK );
        } catch ( BadRequestException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.PUT,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ResponseEntity<?> updateProjection( @PathVariable UUID id, @RequestBody ProjectionDto dto ) {
        try {
            return new ResponseEntity<>( this.projectionService.update( id, dto), HttpStatus.OK );
        } catch ( BadRequestException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

    @RequestMapping( path = "/{id}",
            method = RequestMethod.DELETE )
    public ResponseEntity<?> deleteProjection( @PathVariable UUID id ) {
        try {
            this.projectionService.delete( id );
            return ResponseEntity.ok().build();
        } catch ( NotFoundException e ) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }
}

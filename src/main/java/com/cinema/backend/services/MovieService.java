package com.cinema.backend.services;

import com.cinema.backend.dto.MovieDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Movie;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    Movie getById( UUID id ) throws NotFoundException;
    List<Movie> getMovies( int page, int size );
    List<Movie> search( int page, int size, String name );
    Movie save( MovieDto dto ) throws BadRequestException, ConflictException;
    Movie update( UUID id, MovieDto dto ) throws BadRequestException, NotFoundException ;
    void delete( UUID id ) throws NotFoundException;
}
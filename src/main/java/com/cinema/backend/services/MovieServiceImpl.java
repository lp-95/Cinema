package com.cinema.backend.services;

import com.cinema.backend.dto.MovieDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Movie;
import com.cinema.backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.cinema.backend.exceptions.ErrorMessages.*;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Double DURATION_LIMIT = 1.;
    @Autowired
    private final MovieRepository movieRepository;

    public MovieServiceImpl( MovieRepository movieRepository ) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie getById( UUID id ) throws NotFoundException {
        return this.movieRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( RECORD_NOT_FOUND.getErrorMessage() ) );
    }

    @Override
    public List<Movie> getMovies( int page, int size ) {
        return this.movieRepository.findAll( PageRequest.of( page, size ) ).getContent();
    }

    @Override
    public List<Movie> search( int page, int size, String name ) {
        return this.movieRepository.findAll( name, PageRequest.of( page, size) ).getContent();
    }

    @Override
    public Movie save( MovieDto dto ) throws BadRequestException, ConflictException {
        if ( nameAlreadyExist( dto.getName() ) )
            throw new ConflictException( RECORD_ALREADY_EXIST.getErrorMessage() );
        checkDto( dto );
        Movie movie = new Movie();
        movie.setId( UUID.randomUUID() );
        movie.setName( dto.getName() );
        movie.setDirector( dto.getDirector() );
        movie.setActors( dto.getActors() );
        movie.setGenre( dto.getGenre() );
        movie.setProduction( dto.getProduction() );
        movie.setDescription( dto.getDescription() );
        movie.setDuration( dto.getDuration() );
        return this.movieRepository.save( movie );
    }

    @Override
    public Movie update( UUID id, MovieDto dto ) throws BadRequestException, NotFoundException {
        Movie movie = this.getById( id );
        checkDto( dto );
        movie.setName( dto.getName() );
        movie.setDirector( dto.getDirector() );
        movie.setActors( dto.getActors() );
        movie.setGenre( dto.getGenre() );
        movie.setProduction( dto.getProduction() );
        movie.setDescription( dto.getDescription() );
        movie.setDuration( dto.getDuration() );
        return this.movieRepository.save( movie );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.movieRepository.delete( this.getById( id ) );
    }

    private void checkDto( MovieDto dto ) throws BadRequestException {
        if ( hasEmptyFields( dto ) )
            throw new BadRequestException( CHECK_REQUIRED_FIELDS.getErrorMessage() );
        if ( dto.getDuration() < DURATION_LIMIT )
            throw new BadRequestException( ( INCORRECT_DATE_VALUE.getErrorMessage() ) );
    }

    private boolean nameAlreadyExist( String name ) {
        return this.movieRepository.findByName( name ) != null;
    }

    private boolean hasEmptyFields( MovieDto dto ) {
        return dto.getName().isEmpty() ||
                dto.getDirector().isEmpty() ||
                dto.getActors().isEmpty() ||
                dto.getDuration().toString().isEmpty() ||
                dto.getDescription().isEmpty() ||
                dto.getProduction().isEmpty() ||
                dto.getGenre().toString().isEmpty();
    }

}
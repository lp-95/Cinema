package com.cinema.backend.services;

import com.cinema.backend.dto.ProjectionDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Hall;
import com.cinema.backend.models.Movie;
import com.cinema.backend.models.Projection;
import com.cinema.backend.models.Ticket;
import com.cinema.backend.repositories.ProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.cinema.backend.exceptions.ErrorMessages.*;

@Service
public class ProjectionServiceImpl implements ProjectionService {

    private static final int DISCOUNT_MIN = 0;
    private static final int DISCOUNT_MAX = 100;
    private static final int PRICE_MIN = 1;

    @Autowired
    private final ProjectionRepository projectionRepository;
    @Autowired
    private final MovieServiceImpl movieService;
    @Autowired
    private final HallServiceImpl hallService;

    public ProjectionServiceImpl( ProjectionRepository projectionRepository,
                                  MovieServiceImpl movieService,
                                  HallServiceImpl hallService ) {
        this.projectionRepository = projectionRepository;
        this.movieService = movieService;
        this.hallService = hallService;
    }

    @Override
    public Projection getById( UUID id ) throws NotFoundException {
        return this.projectionRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( RECORD_NOT_FOUND.getErrorMessage() ) );
    }

    @Override
    public List<Projection> getProjections( int page, int size ) {
        return this.projectionRepository.findAll( PageRequest.of( page, size) ).getContent();
    }

    @Override
    public Projection save( ProjectionDto dto ) throws BadRequestException, NotFoundException {
        checkDto( dto );
        Movie movie = this.movieService.getById( dto.getMovie() );
        Hall hall = this.hallService.getById( dto.getHall() );
        Projection projection = new Projection();
        projection.setId( UUID.randomUUID() );
        projection.setMovie( movie );
        projection.setHall( hall );
        projection.setPrice( dto.getPrice() );
        projection.setDiscount( dto.getDiscount() );
        projection.setDate( dto.getDate() );
        generateTickets( projection );
        return this.projectionRepository.save( projection );
    }

    @Override
    public Projection update( UUID id, ProjectionDto dto ) throws BadRequestException, NotFoundException {
        Projection projection = getById( id );
        checkDto( dto );
        Movie movie = this.movieService.getById( dto.getMovie() );
        Hall hall = this.hallService.getById( dto.getHall() );
        projection.setMovie( movie );
        projection.setHall( hall );
        projection.setPrice( dto.getPrice() );
        projection.setDiscount( dto.getDiscount() );
        projection.setDate( dto.getDate() );
        return this.projectionRepository.save( projection );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.projectionRepository.delete( this.getById( id ) );
    }

    private void checkDto( ProjectionDto dto ) throws BadRequestException {
        if ( hasEmptyFields( dto ) ) {
            throw new BadRequestException( CHECK_REQUIRED_FIELDS.getErrorMessage() );
        }
        if ( incorrectPrice( dto.getPrice() ) ) {
            throw new BadRequestException( INCORRECT_PRICE.getErrorMessage() );
        }
        if ( incorrectDiscount( dto.getDiscount() ) ) {
            throw new BadRequestException( INCORRECT_DISCOUNT.getErrorMessage() );
        }
        if ( incorrectDate( dto.getDate() ) )
            throw new BadRequestException( INCORRECT_DATE_VALUE.getErrorMessage() );
    }

    private boolean hasEmptyFields( ProjectionDto dto ) {
        return dto.getMovie().toString().isEmpty() ||
                dto.getHall().toString().isEmpty() ||
                dto.getPrice().toString().isEmpty() ||
                dto.getDate().toString().isEmpty();
    }

    private boolean incorrectPrice( double price ) {
        return price < PRICE_MIN;
    }

    private boolean incorrectDiscount( int discount ) {
        return discount > DISCOUNT_MAX ||
                discount < DISCOUNT_MIN;
    }

    private boolean incorrectDate( Date date ) {
        return date.before( new Date() );
    }

    private void generateTickets( Projection projection ) {
        int rows = projection.getHall().getRows();
        int seats = projection.getHall().getSeats();
        for ( int i = 1; i <= rows; i++ ) {
            for ( int j = 1; j <= seats; j++ ) {
                Ticket ticket = new Ticket();
                ticket.setId( UUID.randomUUID() );
                ticket.setProjection( projection );
                ticket.setRow( i );
                ticket.setSeat( j );
                projection.getTickets().add( ticket );
            }
        }
    }
}
package com.cinema.backend.services;

import com.cinema.backend.dto.HallDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Hall;
import com.cinema.backend.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.cinema.backend.exceptions.ErrorMessages.*;

@Service
public class HallServiceImpl implements HallService {

    private static final int ROWS_LIMIT = 1;
    private static final int SEATS_LIMIT = 1;
    @Autowired
    private final HallRepository hallRepository;

    public HallServiceImpl( HallRepository hallRepository ) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Hall getById(UUID id ) throws NotFoundException {
        return this.hallRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( RECORD_NOT_FOUND.getErrorMessage() ) );
    }

    @Override
    public List<Hall> getHalls( int page, int size ) {
        return this.hallRepository.findAll( PageRequest.of( page, size ) ).getContent();
    }

    @Override
    public List<Hall> search(int page, int size, String name) {
        return this.hallRepository.findAll( name, PageRequest.of( page, size) ).getContent();
    }

    @Override
    public Hall save( HallDto dto ) throws BadRequestException, ConflictException {
        if ( this.hallRepository.findByName( dto.getName() ) != null )
            throw new ConflictException( RECORD_ALREADY_EXIST.getErrorMessage() );
        checkDto( dto );
        Hall hall = new Hall();
        hall.setId( UUID.randomUUID() );
        hall.setName( dto.getName() );
        hall.setRows( dto.getRows() );
        hall.setSeats( dto.getSeats() );
        return this.hallRepository.save( hall );
    }

    @Override
    public Hall update( UUID id, HallDto dto ) throws BadRequestException, NotFoundException {
        Hall hall = this.getById( id );
        checkDto( dto );
        hall.setName( dto.getName() );
        hall.setRows( dto.getRows() );
        hall.setSeats( dto.getSeats() );
        return this.hallRepository.save( hall );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.hallRepository.delete( this.getById( id ) );
    }

    private void checkDto( HallDto dto ) throws BadRequestException {
        if ( hasEmptyFields( dto ) )
            throw new BadRequestException( CHECK_REQUIRED_FIELDS.getErrorMessage() );
        if ( incorrectRowsAndSeats( dto.getRows(), dto.getSeats() ) )
            throw new BadRequestException( INCORRECT_NUMBERS_ADDED.getErrorMessage() );
    }

    private boolean hasEmptyFields( HallDto dto ) {
        return dto.getName().isEmpty() ||
                dto.getRows().toString().isEmpty() ||
                dto.getSeats().toString().isEmpty();
    }

    private boolean incorrectRowsAndSeats( int rows, int seats ) {
        return rows < ROWS_LIMIT || seats < SEATS_LIMIT;
    }
}
package com.cinema.backend.services;

import com.cinema.backend.dto.HallDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Hall;

import java.util.List;
import java.util.UUID;

public interface HallService {

    Hall getById( UUID id ) throws NotFoundException;
    List<Hall> getHalls( int page, int size );
    List<Hall> search( int page, int size, String name );
    Hall save( HallDto dto ) throws BadRequestException, ConflictException;
    Hall update( UUID id, HallDto dto ) throws BadRequestException, NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
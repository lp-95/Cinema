package com.cinema.backend.services;

import com.cinema.backend.dto.ProjectionDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Projection;

import java.util.List;
import java.util.UUID;

public interface ProjectionService {

    Projection getById( UUID id ) throws NotFoundException;
    List<Projection> getProjections(int page, int size );
    Projection save( ProjectionDto dto ) throws BadRequestException, NotFoundException;
    Projection update( UUID id, ProjectionDto dto ) throws BadRequestException, NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
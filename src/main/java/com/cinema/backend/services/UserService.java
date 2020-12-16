package com.cinema.backend.services;

import com.cinema.backend.dto.UserDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    List<User> getUsers( int page, int size );
    List<User> search( int page, int size , String firstName );
    User findById( UUID id ) throws NotFoundException;
    User save( UserDto dto ) throws BadRequestException, ConflictException;
    User update( UUID id, UserDto dto ) throws BadRequestException, NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
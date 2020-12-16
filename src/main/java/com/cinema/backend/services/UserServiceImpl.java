package com.cinema.backend.services;

import com.cinema.backend.dto.UserDto;
import com.cinema.backend.exceptions.BadRequestException;
import com.cinema.backend.exceptions.ConflictException;
import com.cinema.backend.exceptions.NotFoundException;
import com.cinema.backend.models.Admin;
import com.cinema.backend.models.Customer;
import com.cinema.backend.models.TicketSeller;
import com.cinema.backend.models.User;
import com.cinema.backend.repositories.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.cinema.backend.exceptions.ErrorMessages.*;

@Service
public class UserServiceImpl implements UserService {

    private static final int PASSWORD_LIMIT = 8;
    private static final int PASSWORD_DIGITS = 3;
    private static final int NAME_DIGITS = 0;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl( UserRepository userRepository, BCryptPasswordEncoder encoder ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public List<User> getUsers( int page, int size ) {
        return this.userRepository.findAll( PageRequest.of( page, size ) ).getContent();
    }

    @Override
    public List<User> search( int page, int size, String firstName ) {
        return this.userRepository.findAll( firstName, PageRequest.of( page, size) ).getContent();
    }

    @Override
    public User findById( UUID id ) throws NotFoundException {
        return this.userRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( USER_NOT_FOUND.getErrorMessage() ));
    }

    @Override
    public User save( UserDto dto ) throws BadRequestException, ConflictException {
        if ( findByEmail( dto.getEmail() ) != null ) {
            throw new ConflictException( USER_ALREADY_EXIST.getErrorMessage() );
        }
        checkDto( dto );
        User user;
        if ( dto.getAdmin() ) {
            user = new Admin();
        } else if ( dto.getTicketSeller() ) {
            user = new TicketSeller();
        } else {
            user = new Customer();
        }
        user.setId( UUID.randomUUID() );
        user.setEmail( dto.getEmail() );
        user.setPassword( this.encoder.encode( dto.getPassword() ) );
        user.setFirstName( dto.getFirstName() );
        user.setSurName( dto.getSurName() );
        user.setGender( dto.getGender() );
        user.setBorn( dto.getBorn() );
        return this.userRepository.save( user );
    }

    @Override
    public User update( UUID id, UserDto dto ) throws BadRequestException, NotFoundException {
        User user = findById( id );
        checkDto( dto );
        user.setPassword( this.encoder.encode( dto.getPassword() ) );
        user.setFirstName( dto.getFirstName() );
        user.setSurName( dto.getSurName() );
        user.setGender( dto.getGender() );
        user.setBorn( dto.getBorn() );
        return this.userRepository.save( user );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.userRepository.delete( this.findById( id ) );
    }

    @Override
    public UserDetails loadUserByUsername(String email ) throws UsernameNotFoundException {
        User foundUser = this.userRepository.getByEmail( email )
                .orElseThrow( () -> new UsernameNotFoundException( BAD_CREDENTIALS.getErrorMessage() ) );
        return org.springframework.security.core.userdetails
                .User
                .builder()
                .username( foundUser.getEmail() )
                .password( foundUser.getPassword() )
                .build();
    }

    public User findByEmail( String email ) {
        return this.userRepository.findByEmail( email );
    }

    private void checkDto( UserDto dto ) throws BadRequestException {
        if ( hasEmptyFields( dto ) ) {
            throw new BadRequestException( CHECK_REQUIRED_FIELDS.getErrorMessage() );
        }
        if ( incorrectEmail( dto.getEmail() ) ) {
            throw new BadRequestException( INCORRECT_EMAIL_FORMAT.getErrorMessage() );
        }
        if ( incorrectPassword( dto.getPassword() ) ) {
            throw new BadRequestException( INCORRECT_PASSWORD_FORMAT.getErrorMessage() );
        }
        if ( passwordsNotMatches( dto.getPassword(), dto.getMatchingPassword() ) ) {
            throw new BadRequestException( PASSWORDS_NOT_MATCHES.getErrorMessage() );
        }
        if ( incorrectName( dto.getFirstName() ) || incorrectName( dto.getSurName() ) ) {
            throw new BadRequestException( INCORRECT_NAME_FORMAT.getErrorMessage() );
        }
        if( incorrectDate( dto.getBorn() ) ) {
            throw new BadRequestException( INCORRECT_DATE_VALUE.getErrorMessage() );
        }
    }

    private boolean incorrectDate( Date date ) {
        return date.after( new Date() );
    }

    private boolean incorrectEmail( String email ) {
        return !EmailValidator.getInstance().isValid( email );
    }

    private boolean hasEmptyFields( UserDto dto ) {
        return dto.getEmail().isEmpty() ||
                dto.getPassword().isEmpty() ||
                dto.getMatchingPassword().isEmpty() ||
                dto.getFirstName().isEmpty() ||
                dto.getSurName().isEmpty() ||
                dto.getGender().toString().isEmpty() ||
                dto.getBorn().toString().isEmpty();
    }

    private boolean passwordsNotMatches( String password, String repeated ) {
        return !password.equals( repeated );
    }

    private boolean incorrectPassword( String password ) {
        return password.length() < PASSWORD_LIMIT || countDigits( password ) < PASSWORD_DIGITS;
    }

    private boolean incorrectName( String name ) {
        return countDigits( name ) > NAME_DIGITS;
    }

    private int countDigits( String s ) {
        int counter = 0;
        for ( int i = 1; i < s.length(); i ++ ) {
            if ( Character.isDigit( s.charAt( i ) ) ) {
                counter ++;
            }
        }
        return counter;
    }
}
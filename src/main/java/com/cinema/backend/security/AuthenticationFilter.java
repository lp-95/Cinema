package com.cinema.backend.security;

import com.cinema.backend.dto.SignInRequest;
import com.cinema.backend.exceptions.BadCredentialsException;
import com.cinema.backend.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.cinema.backend.security.SecurityConstants.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public AuthenticationFilter( AuthenticationManager authenticationManager ) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication( HttpServletRequest request,
                                                 HttpServletResponse response) throws AuthenticationException {
        try {
            SignInRequest signIn = new ObjectMapper().readValue( request.getInputStream(), SignInRequest.class );
            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signIn.getEmail(), signIn.getPassword(), new ArrayList<>() ));
        } catch ( IOException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response,
                                             FilterChain chain, Authentication authResult ) throws IOException, ServletException {
        String email = ( (User) authResult.getPrincipal() ).getUsername();
        String token = Jwts.builder()
                .setSubject( email )
                .setExpiration( new Date( System.currentTimeMillis()  + EXPIRATION_TIME ))
                .signWith( SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret() ).compact();
        UserServiceImpl service = ( UserServiceImpl ) ApplicationContextImpl.getBean( "userService" );
        com.cinema.backend.models.User user;
        try {
            user = service.findByEmail( email );
            response.addHeader( TOKEN_HEADER, TOKEN_PREFIX + token );
            response.addHeader( "UserID", String.valueOf( user.getId() ));
        } catch ( BadCredentialsException ex ) {
            ex.printStackTrace();
        }
    }
}

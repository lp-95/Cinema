package com.cinema.backend.Security;

import com.cinema.backend.services.UserService;
import com.cinema.backend.services.UserServiceImpl;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.cinema.backend.Security.SecurityConstants.SIGN_IN_URL;
import static com.cinema.backend.Security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final BCryptPasswordEncoder encoder;
    private final UserServiceImpl userService;

    public ApplicationSecurityConfig( BCryptPasswordEncoder encoder, UserServiceImpl userService ) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers( HttpMethod.POST, SIGN_UP_URL )
                .permitAll().anyRequest().authenticated().and()
                .addFilter( getAuthenticationFilter() )
                .addFilter( new AuthorizationFilter( authenticationManager() ))
                .sessionManagement()
                .sessionCreationPolicy( SessionCreationPolicy.STATELESS );
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( this.userService ).passwordEncoder( this.encoder );
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter( authenticationManager() );
        filter.setFilterProcessesUrl( SIGN_IN_URL );
        return filter;
    }
}
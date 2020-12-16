package com.cinema.backend;

import com.cinema.backend.Security.ApplicationContextImpl;
import com.cinema.backend.Security.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ApplicationContextImpl applicationContextImpl() {
		return new ApplicationContextImpl();
	}

	@Bean( name = "ApplicationProperties" )
	public ApplicationProperties getApplicationProperties() {
		return new ApplicationProperties();
	}
}

package com.cinema.backend.repositories;

import com.cinema.backend.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Movie findByName( String name );
    @Query( "SELECT m FROM Movie m WHERE CONCAT ( m.name ) LIKE %?1%" )
    Page<Movie> findAll( String name, Pageable pageable );
}
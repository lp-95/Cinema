package com.cinema.backend.repositories;

import com.cinema.backend.models.Hall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HallRepository extends JpaRepository<Hall, UUID> {
    Hall findByName( String name );
    @Query( "SELECT h FROM Hall h WHERE CONCAT( h.name ) LIKE %?1%" )
    Page<Hall> findAll(String name, Pageable pageable );
}
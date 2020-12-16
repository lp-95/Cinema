package com.cinema.backend.repositories;

import com.cinema.backend.models.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, UUID> {

}
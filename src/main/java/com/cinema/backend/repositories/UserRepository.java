package com.cinema.backend.repositories;

import com.cinema.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail( String email );
    Optional<User> getByEmail( String email );
    @Query( "SELECT u FROM User u WHERE CONCAT ( u.firstName, u.surName, u.email ) LIKE %?1%" )
    Page<User> findAll( String firstName, Pageable pageable );
}
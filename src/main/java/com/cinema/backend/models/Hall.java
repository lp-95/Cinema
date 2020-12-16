package com.cinema.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table( name = "halls" )
public class Hall {

    @Id
    @Column( unique = true, nullable = false )
    @Type( type = "uuid-char" )
    private UUID id;
    @Column( unique = true, nullable = false )
    private String name;
    @Column( name = "rows_in_hall", nullable = false )
    private Integer rows;
    @Column( nullable = false )
    private Integer seats;
    @OneToMany( mappedBy = "hall", fetch = FetchType.LAZY )
    private List<Projection> projection;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "admin" )
    private Admin admin;

    public Hall() {
    }

    public Hall( UUID id, String name, Integer rows, Integer seats, List<Projection> projection, Admin admin ) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.seats = seats;
        this.projection = projection;
        this.admin = admin;
    }

    public UUID getId() {
        return id;
    }

    public void setId( UUID id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows( Integer rows ) {
        this.rows = rows;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats( Integer seats ) {
        this.seats = seats;
    }

    public List<Projection> getProjection() {
        return projection;
    }

    public void setProjection( List<Projection> projection ) {
        this.projection = projection;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin( Admin admin ) {
        this.admin = admin;
    }
}
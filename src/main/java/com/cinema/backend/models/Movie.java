package com.cinema.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table( name = "movies" )
public class Movie {

    @Id
    @Column( unique = true, nullable = false )
    @Type( type = "uuid-char" )
    private UUID id;
    @Column( unique = true, nullable = false )
    private String name;
    @Column( nullable = false )
    private String director;
    @Column( nullable = false )
    private String actors;
    @Column( nullable = false )
    @Enumerated( EnumType.STRING )
    private Genre genre;
    @Column( nullable = false )
    private String production;
    @Column( nullable = false )
    private String description;
    @Column( nullable = false )
    private Double duration;
    @OneToMany( mappedBy = "movie", fetch = FetchType.LAZY )
    private List<Projection> projections;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "admin_id" )
    private Admin admin;

    public Movie() {
    }

    public Movie( UUID id, String name, String director, String actors, Genre genre,
                  String production, String description, Double duration, List<Projection> projections, Admin admin ) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.production = production;
        this.description = description;
        this.duration = duration;
        this.projections = projections;
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

    public String getDirector() {
        return director;
    }

    public void setDirector( String director ) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors( String actors ) {
        this.actors = actors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre( Genre genre ) {
        this.genre = genre;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction( String production ) {
        this.production = production;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration( Double duration ) {
        this.duration = duration;
    }

    public List<Projection> getProjections() {
        return projections;
    }

    public void setProjections( List<Projection> projections ) {
        this.projections = projections;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin( Admin admin ) {
        this.admin = admin;
    }
}

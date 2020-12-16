package com.cinema.backend.dto;

import com.cinema.backend.models.Genre;

public class MovieDto {

    private String name;
    private String director;
    private String actors;
    private Genre genre;
    private String production;
    private String description;
    private Double duration;

    public MovieDto() {
    }

    public MovieDto( String name, String director, String actors, Genre genre,
                     String production, String description, Double duration ) {
        this.name = name;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.production = production;
        this.description = description;
        this.duration = duration;
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
}
package com.cinema.backend.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Admin extends User {

    @OneToMany( mappedBy = "admin", fetch =  FetchType.LAZY )
    private List<Movie> movies;
    @OneToMany( mappedBy = "admin", fetch = FetchType.LAZY )
    private List<Projection> projections;
    @OneToMany( mappedBy = "admin", fetch = FetchType.LAZY )
    private List<Hall> hales;

    public Admin() {
        this.movies = new ArrayList<>();
        this.projections = new ArrayList<>();
        this.hales = new ArrayList<>();
    }

    public Admin( UUID id, String email, String password,
                  String firstName, String surName, Genders gender, Date born ) {
        super( id, email, password, firstName, surName, gender, born );
        this.movies = new ArrayList<>();
        this.projections = new ArrayList<>();
        this.hales = new ArrayList<>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies( List<Movie> movies ) {
        this.movies = movies;
    }

    public List<Projection> getProjections() {
        return projections;
    }

    public void setProjections( List<Projection> projections ) {
        this.projections = projections;
    }

    public List<Hall> getHales() {
        return hales;
    }

    public void setHales( List<Hall> hales ) {
        this.hales = hales;
    }

}
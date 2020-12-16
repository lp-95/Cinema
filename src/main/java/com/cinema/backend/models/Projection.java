package com.cinema.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table( name = "projections" )
public class Projection {

    @Id
    @Column( unique = true, nullable = false )
    @Type( type = "uuid-char" )
    private UUID id;
    @Column( nullable = false )
    private Double price;
    @Column
    private Integer discount;
    @Column
    private Date date;
    @OneToMany( mappedBy = "projection", fetch = FetchType.LAZY )
    private List<Ticket> tickets;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "movie" )
    private Movie movie;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "hall" )
    private Hall hall;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "admin" )
    private Admin admin;

    public Projection() {
        this.tickets = new ArrayList<>();
    }

    public Projection( UUID id, Double price, Integer discount, Date date,
                       List<Ticket> tickets, Movie movie, Hall hall, Admin admin ) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.date = date;
        this.tickets = tickets;
        this.movie = movie;
        this.hall = hall;
        this.admin = admin;
        this.tickets = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId( UUID id ) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice( Double price ) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount( Integer discount ) {
        this.discount = discount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets( List<Ticket> tickets ) {
        this.tickets = tickets;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie( Movie movie ) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall( Hall hall ) {
        this.hall = hall;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin( Admin admin ) {
        this.admin = admin;
    }
}
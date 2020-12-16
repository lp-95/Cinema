package com.cinema.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public class ProjectionDto {

    private UUID movie;
    private UUID hall;
    private Double price;
    private Integer discount;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy-hh-mm" )
    private Date date;

    public ProjectionDto() {
    }

    public ProjectionDto( UUID movie, UUID hall, Double price, Integer discount, Date date ) {
        this.movie = movie;
        this.hall = hall;
        this.price = price;
        this.discount = discount;
        this.date = date;
    }

    public UUID getMovie() {
        return movie;
    }

    public void setMovie( UUID movie ) {
        this.movie = movie;
    }

    public UUID getHall() {
        return hall;
    }

    public void setHall( UUID hall ) {
        this.hall = hall;
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
}
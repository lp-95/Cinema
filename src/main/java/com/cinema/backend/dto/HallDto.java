package com.cinema.backend.dto;

public class HallDto {

    private String name;
    private Integer rows;
    private Integer seats;

    public HallDto() {
    }

    public HallDto( String name, Integer rows, Integer seats ) {
        this.name = name;
        this.rows = rows;
        this.seats = seats;
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
}

package com.cinema.backend.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Customer extends User{

    @OneToMany( mappedBy = "customer", fetch = FetchType.LAZY )
    private List<Ticket> tickets;

    public Customer() {
        this.tickets = new ArrayList<>();
    }

    public Customer( UUID id, String email, String password, String firstName,
                     String surName, Genders gender, Date born ) {
        super( id, email, password, firstName, surName, gender, born );
        this.tickets = new ArrayList<>();
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets( List<Ticket> tickets ) {
        this.tickets = tickets;
    }
}

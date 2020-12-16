package com.cinema.backend.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class TicketSeller extends User {

    @OneToMany( mappedBy = "ticketSeller", fetch = FetchType.LAZY )
    private List<Ticket> tickets;

    public TicketSeller() {
        this.tickets = new ArrayList<>();
    }

    public TicketSeller(UUID id, String email, String password,
                        String firstName, String surName, Genders gender, Date born ) {
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

package com.cinema.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table( name = "tickets" )
public class Ticket {

    @Id
    @Column( unique = true, nullable = false )
    @Type( type = "uuid-char" )
    private UUID id;
    @Column( name = "hall_row" )
    private Integer row;
    @Column
    private Integer seat;
    @Column
    private Integer discount;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "projection" )
    private Projection projection;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "customer_id" )
    private Customer customer;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ticket_seller_id" )
    private TicketSeller ticketSeller;

    public Ticket() {
    }

    public Ticket( UUID id, Integer row, Integer seat, Integer discount,
                   Projection projection, Customer customer, TicketSeller ticketSeller ) {
        this.id = id;
        this.row = row;
        this.seat = seat;
        this.discount = discount;
        this.projection = projection;
        this.customer = customer;
        this.ticketSeller = ticketSeller;
    }

    public UUID getId() {
        return id;
    }

    public void setId( UUID id ) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow( Integer row ) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat( Integer seat ) {
        this.seat = seat;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount( Integer discount ) {
        this.discount = discount;
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection( Projection projection ) {
        this.projection = projection;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer( Customer customer ) {
        this.customer = customer;
    }

    public TicketSeller getTicketSeller() {
        return ticketSeller;
    }

    public void setTicketSeller( TicketSeller ticketSeller ) {
        this.ticketSeller = ticketSeller;
    }
}
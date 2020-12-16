package com.cinema.backend.dto;

public class TicketDto {

    private Integer discount;

    public TicketDto() {
    }

    public TicketDto(Integer discount ) {
        this.discount = discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount( Integer discount ) {
        this.discount = discount;
    }
}
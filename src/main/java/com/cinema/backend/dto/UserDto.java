package com.cinema.backend.dto;

import com.cinema.backend.models.Genders;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserDto {

    private String email;
    private String password;
    private String matchingPassword;
    private String firstName;
    private String surName;
    private Genders gender;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy" )
    private Date born;
    private Boolean admin;
    private Boolean ticketSeller;

    public UserDto() {
    }

    public UserDto( String email, String password, String matchingPassword,
                    String firstName, String surName, Genders gender,
                    Date born, Boolean admin, Boolean ticketSeller ) {
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.firstName = firstName;
        this.surName = surName;
        this.gender = gender;
        this.born = born;
        this.admin = admin;
        this.ticketSeller = ticketSeller;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword( String matchingPassword ) {
        this.matchingPassword = matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName( String surName ) {
        this.surName = surName;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender( Genders gender ) {
        this.gender = gender;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn( Date born ) {
        this.born = born;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin( Boolean admin ) {
        this.admin = admin;
    }

    public Boolean getTicketSeller() {
        return ticketSeller;
    }

    public void setTicketSeller( Boolean ticketSeller ) {
        this.ticketSeller = ticketSeller;
    }
}
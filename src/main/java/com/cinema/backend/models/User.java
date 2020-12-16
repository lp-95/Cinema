package com.cinema.backend.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Table( name = "users" )
public abstract class User {

    @Id
    @Column( unique = true, nullable = false )
    @Type( type = "uuid-char" )
    private UUID id;
    @Column( unique = true, nullable = false )
    private String email;
    @Column( nullable = false )
    private String password;
    @Column( name = "first_name", nullable = false )
    private String firstName;
    @Column( name = "sur_name", nullable = false )
    private String surName;
    @Column( nullable = false )
    @Enumerated( EnumType.STRING )
    private Genders gender;
    @Column( nullable = false )
    private Date born;

    public User() {
    }

    public User( UUID id, String email, String password, String firstName, String surName, Genders gender, Date born ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.surName = surName;
        this.gender = gender;
        this.born = born;
    }

    public UUID getId() {
        return id;
    }

    public void setId( UUID id ) {
        this.id = id;
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

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn( Date born ) {
        this.born = born;
    }
}
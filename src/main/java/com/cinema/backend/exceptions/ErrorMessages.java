package com.cinema.backend.exceptions;

public enum ErrorMessages {
    BAD_CREDENTIALS( "Given credentials are incorrect" ),
    CHECK_REQUIRED_FIELDS( "Required fields can not be empty. Please fill in the missing fields" ),
    RECORD_ALREADY_EXIST( "Record with given name is already exist" ),
    USER_ALREADY_EXIST( "User with given email is already exist" ),
    RECORD_NOT_FOUND( "Record with given ID is not found" ),
    USER_NOT_FOUND( "User with given ID is not found" ),
    INCORRECT_EMAIL_FORMAT( "Given email address is not verified" ),
    INCORRECT_NAME_FORMAT( "First name and surname must contains only letters" ),
    INCORRECT_PASSWORD_FORMAT( "Password must contain at least 8 characters and at least 3 digits" ),
    INCORRECT_NUMBERS_ADDED( "Given numbers are incorrect" ),
    INCORRECT_DATE_VALUE( "The date and time must be realistic" ),
    INCORRECT_PRICE( "The price must be a positive number" ),
    INCORRECT_DISCOUNT( "The discount must be 0 - 100 percentage" ),
    PASSWORDS_NOT_MATCHES( "Passwords not matches" );

    private String errorMessage;

    ErrorMessages( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
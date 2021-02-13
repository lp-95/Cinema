package com.cinema.backend.security;

public class SecurityConstants {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_IN_URL = "/users/sign-in";
    public static final String SIGN_UP_URL = "/users";
    public static final Integer EXPIRATION_TIME = 900_000; // 15 minutes in milliseconds

    public static String getTokenSecret() {
        ApplicationProperties properties =
                (ApplicationProperties) ApplicationContextImpl.getBean( "ApplicationProperties" );
        return properties.getTokenSecret();
    }
}
package io.javajason.springsecurityjwt.models;

import java.util.Objects;

public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }



    public String getJwt() {
        return this.jwt;
    }


    
}
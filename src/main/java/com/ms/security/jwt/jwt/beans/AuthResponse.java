package com.ms.security.jwt.jwt.beans;
/**
 *  AuthResponse bean will carry generated JWT token after successful authentication
 **/
public class AuthResponse {
    private String jwtToken;
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

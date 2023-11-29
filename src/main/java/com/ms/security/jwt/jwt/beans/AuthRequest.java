package com.ms.security.jwt.jwt.beans;

/**
 * AuthRequest bean will carry the userName and password sent from client when login first time...
    After successful authentication Application will generate the JWT token
 **/

public class AuthRequest {
    private String userName;
    private String password;

    public AuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

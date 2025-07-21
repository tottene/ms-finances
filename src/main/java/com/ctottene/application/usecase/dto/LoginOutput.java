package com.ctottene.application.usecase.dto;

public class LoginOutput {
    private String token;
    private String role;

    public LoginOutput(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() { return token; }
    public String getRole() { return role; }
}
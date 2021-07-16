package com.example.scheduling.Controllers.util;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthenticateResponse {

    private String token;
    private Collection<? extends GrantedAuthority> role;

    public AuthenticateResponse(String token) {
        this.token = token;
    }

    public AuthenticateResponse(String token, Collection<? extends GrantedAuthority> role) {
        this.token = token;
        this.role = role;
    }

    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}

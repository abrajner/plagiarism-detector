package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class AuthenticationTokenDto {
    
    private String authenticationToken;
    
    public String getAuthenticationToken() {
        return this.authenticationToken;
    }
    
    public void setAuthenticationToken(final String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
    
    @Override
    public String toString() {
        return "AuthenticationTokenDto{" +
                "authenticationToken='" + authenticationToken + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final AuthenticationTokenDto that = (AuthenticationTokenDto) o;
        return Objects.equals(this.authenticationToken, that.authenticationToken);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.authenticationToken);
    }
}

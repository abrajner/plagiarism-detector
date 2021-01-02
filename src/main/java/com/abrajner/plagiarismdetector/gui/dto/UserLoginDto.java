package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class UserLoginDto {
    
    private String password;
    
    private String login;
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(final String login) {
        this.login = login;
    }
    
    @Override
    public String toString() {
        return "UserLoginDto{" +
                "password='" + this.password + '\'' +
                ", login='" + this.login + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final UserLoginDto that = (UserLoginDto) o;
        return Objects.equals(this.password, that.password) &&
                Objects.equals(this.login, that.login);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.password, this.login);
    }
}

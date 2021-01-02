package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class UserRegistrationDto {
    
    private String login;
    
    private String firstName;
    
    private String lastName;
    
    private String password;
    
    private String repeatedPassword;
    
    private String email;
    
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(final String login) {
        this.login = login;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getRepeatedPassword() {
        return this.repeatedPassword;
    }
    
    public void setRepeatedPassword(final String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "login='" + this.login + '\'' +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", password='" + this.password + '\'' +
                ", repeatedPassword='" + this.repeatedPassword + '\'' +
                ", email='" + this.email + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final UserRegistrationDto that = (UserRegistrationDto) o;
        return Objects.equals(this.login, that.login) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.repeatedPassword, that.repeatedPassword) &&
                Objects.equals(this.email, that.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.login, this.firstName, this.lastName, this.password, this.repeatedPassword, this.email);
    }
}

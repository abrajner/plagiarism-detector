package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class UserDto {
    
    private Long id;
    
    private String login;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private boolean isActive;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
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
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public boolean isActive() {
        return this.isActive;
    }
    
    public void setActive(final boolean active) {
        this.isActive = active;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final UserDto userDto = (UserDto) o;
        return this.isActive == userDto.isActive &&
                Objects.equals(this.id, userDto.id) &&
                Objects.equals(this.login, userDto.login) &&
                Objects.equals(this.firstName, userDto.firstName) &&
                Objects.equals(this.lastName, userDto.lastName) &&
                Objects.equals(this.email, userDto.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.login, this.firstName, this.lastName, this.email, this.isActive);
    }
}

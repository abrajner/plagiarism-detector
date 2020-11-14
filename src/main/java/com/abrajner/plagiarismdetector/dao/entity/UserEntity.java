package com.abrajner.plagiarismdetector.dao.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.abrajner.plagiarismdetector.common.Defaults;

@Entity
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity implements Serializable {
    
    static final String TABLE_NAME = "users";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Defaults.CommonEntityColumns.ID)
    private Long id;
    
    @Column(name = Defaults.UserEntityColumns.LOGIN, nullable = false)
    private String login;
    
    @Column(name = Defaults.UserEntityColumns.PASSWORD, nullable = false)
    private String password;
    
    @Column(name = Defaults.UserEntityColumns.FIRST_NAME, nullable = false)
    private String firstName;
    
    @Column(name = Defaults.UserEntityColumns.LAST_NAME, nullable = false)
    private String lastName;
    
    @Column(name = Defaults.UserEntityColumns.EMAIL, nullable = false)
    private String email;
    
    @Column(name = Defaults.UserEntityColumns.IS_ACTIVE)
    private boolean isActive;
    
    public UserEntity(){
    }
    
    private UserEntity(final Builder builder){
        this.setId(builder.id);
        this.setLogin(builder.login);
        this.setPassword(builder.password);
        this.setFirstName(builder.firstName);
        this.setLastName(builder.lastName);
        this.setEmail(builder.email);
        this.setActive(builder.isActive);
    }
    
    public void setId(final Long userId) {
        this.id = userId;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(final String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
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
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setFirstName(final String name){
        this.firstName = name;
    }
    
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + this.id +
                ", login='" + this.login + '\'' +
                ", password='" + this.password + '\'' +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", email='" + this.email + '\'' +
                ", isActive=" + this.isActive +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final UserEntity that = (UserEntity) o;
        return this.isActive == that.isActive &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.login, that.login) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.email, that.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.login, this.password, this.firstName, this.lastName, this.email, this.isActive);
    }
    
    public static final class Builder {
        
        private Long id;
        
        private String login;
        
        private String password;
        
        private String firstName;
        
        private String lastName;
        
        private String email;
        
        private boolean isActive;
        
        public Builder(){
        }
        
        public Builder id(final Long id){
            this.id = id;
            return this;
        }
        
        public Builder login(final String login){
            this.login = login;
            return this;
        }
    
        public Builder password(final String password){
            this.password = password;
            return this;
        }
    
        public Builder firstName(final String firstName){
            this.firstName = firstName;
            return this;
        }
    
        public Builder lastName(final String lastName){
            this.lastName = lastName;
            return this;
        }
    
        public Builder email(final String email){
            this.email = email;
            return this;
        }
    
        public Builder isActive(final boolean isActive){
            this.isActive = isActive;
            return this;
        }
        
        public UserEntity build(){
            return new UserEntity(this);
        }
    }
}

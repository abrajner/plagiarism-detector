package com.abrajner.plagiarismdetector.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.abrajner.plagiarismdetector.gui.dto.UserLoginDto;

@Component
public class UserLoginValidator extends Validator {
    
    @Override
    public void validate(final Object o) {
        this.validate((UserLoginDto) o);
    }
    
    private void validate(final UserLoginDto u){
        this.validateLoginAndPassword(u.getLogin(), u.getPassword());
    }
    
    private void validateLoginAndPassword(final String login, final String password){
        try{
            this.validateMandatoryTextField(login, "login");
            this.validatePassword(password);
        } catch (final ResponseStatusException ex){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect login or password");
        }
    }
}

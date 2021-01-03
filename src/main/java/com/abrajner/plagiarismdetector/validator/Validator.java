package com.abrajner.plagiarismdetector.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class Validator {
    
    public abstract void validate (Object o);
    
    protected void validateMandatoryTextField(final String text, final String fieldName){
        if(text.isEmpty() || text.length()>=50){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect " + fieldName);
        }
    }
    protected void validateOptionalTextField(final String text, final String fieldName){
        if(text.length()>=50){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect " + fieldName);
        }
    }
    
    protected void validateEmail(final String email){
        if(!EmailValidator.getInstance(true).isValid(email)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect email");
        }
    }
    
    protected void validateRepeatedPassword(final String password, final String repeatedPassword){
        if(!password.equals(repeatedPassword)){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Repeated password doesn't match original password");
        }
    }
    
    protected void validatePassword(final String password){
        if(password.length()<4){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Password needs to be longer than 4");
        }
    }
}

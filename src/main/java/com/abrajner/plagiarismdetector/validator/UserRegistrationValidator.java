package com.abrajner.plagiarismdetector.validator;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.gui.dto.UserRegistrationDto;

@Component
public class UserRegistrationValidator extends AbstractValidator {
    
    @Override
    public void validate(final Object o) {
        this.validate((UserRegistrationDto) o);
    }
    
    private void validate(final UserRegistrationDto o) {
        this.validateMandatoryTextField(o.getLogin(), "login");
        this.validateEmail(o.getEmail());
        this.validatePassword(o.getPassword());
        this.validateRepeatedPassword(o.getPassword(), o.getRepeatedPassword());
        this.validateOptionalTextField(o.getFirstName(), "first name");
        this.validateOptionalTextField(o.getLastName(), "last name");
    }
}

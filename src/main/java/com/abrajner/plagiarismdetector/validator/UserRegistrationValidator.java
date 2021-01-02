package com.abrajner.plagiarismdetector.validator;

import com.abrajner.plagiarismdetector.gui.dto.UserRegistrationDto;

public class UserRegistrationValidator extends Validator {
    
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

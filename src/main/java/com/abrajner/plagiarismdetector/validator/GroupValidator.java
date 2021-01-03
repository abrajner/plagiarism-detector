package com.abrajner.plagiarismdetector.validator;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.gui.dto.GroupDto;

@Component
public class GroupValidator extends Validator {
    
    @Override
    public void validate(final Object o) {
        this.validate((GroupDto) o);
    }
    
    private void validate(final GroupDto groupDto){
        this.validateMandatoryTextField(groupDto.getGroupName(), "group name");
    }
}

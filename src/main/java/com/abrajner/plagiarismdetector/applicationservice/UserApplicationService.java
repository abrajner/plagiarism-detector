package com.abrajner.plagiarismdetector.applicationservice;

import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.gui.dto.UserRegistrationDto;

public interface UserApplicationService {
    
    UserDto validateAndSaveNewUser(UserRegistrationDto userRegistrationDto);
}

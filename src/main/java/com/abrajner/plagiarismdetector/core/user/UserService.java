package com.abrajner.plagiarismdetector.core.user;

import com.abrajner.plagiarismdetector.dao.entity.UserEntity;
import com.abrajner.plagiarismdetector.gui.dto.UserLoginDto;
import com.abrajner.plagiarismdetector.gui.dto.UserRegistrationDto;

public interface UserService {
    
    UserEntity checkUserLoginCredentials(UserLoginDto userLoginDto);
    
    UserEntity findUserId(Long id);
    
    UserEntity saveNewUserInDatabase(UserRegistrationDto userRegistrationDto);
    
    void checkIfNewUserAlreadyExistsInDatabase(String login, String email);
}

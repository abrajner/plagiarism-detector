package com.abrajner.plagiarismdetector.core.user;

import com.abrajner.plagiarismdetector.dao.entity.UserEntity;
import com.abrajner.plagiarismdetector.gui.dto.UserLoginDto;

public interface UserService {
    
    UserEntity checkUserLoginCredentials(UserLoginDto userLoginDto);
    
    UserEntity findUserId(Long id);
}

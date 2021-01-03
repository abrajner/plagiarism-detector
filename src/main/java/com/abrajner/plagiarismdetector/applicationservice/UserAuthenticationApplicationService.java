package com.abrajner.plagiarismdetector.applicationservice;

import com.abrajner.plagiarismdetector.gui.dto.AuthenticationTokenDto;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.gui.dto.UserLoginDto;

public interface UserAuthenticationApplicationService {
    
    AuthenticationTokenDto generateUserAuthenticationToken(UserLoginDto userLoginDto);
    
    UserDto checkAuthenticationToken(String token);
}

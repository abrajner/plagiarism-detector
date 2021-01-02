package com.abrajner.plagiarismdetector.applicationservice.impl;

import org.springframework.stereotype.Service;

import com.abrajner.plagiarismdetector.applicationservice.UserApplicationService;
import com.abrajner.plagiarismdetector.core.user.UserService;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.gui.dto.UserRegistrationDto;
import com.abrajner.plagiarismdetector.mapper.UserMapper;
import com.abrajner.plagiarismdetector.validator.UserRegistrationValidator;
import com.abrajner.plagiarismdetector.validator.Validator;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    
    private final UserMapper userMapper;
    
    private final UserService userService;
    
    private final Validator validator;
    
    public UserApplicationServiceImpl(final UserMapper userMapper,
                                      final UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.validator = new UserRegistrationValidator();
    }
    
    @Override
    public UserDto validateAndSaveNewUser(final UserRegistrationDto userRegistrationDto){
        this.checkIfAlreadyUserExistsInDatabase(userRegistrationDto.getLogin(), userRegistrationDto.getEmail());
        this.validator.validate(userRegistrationDto);
        return this.userMapper.convertToDto(this.userService.saveNewUserInDatabase(userRegistrationDto));
    }
    
    private void checkIfAlreadyUserExistsInDatabase(final String login, final String email){
        this.userService.checkIfNewUserAlreadyExistsInDatabase(login, email);
    }
}

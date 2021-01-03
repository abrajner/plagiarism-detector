package com.abrajner.plagiarismdetector.applicationservice.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.common.JwtUtil;
import com.abrajner.plagiarismdetector.core.user.UserService;
import com.abrajner.plagiarismdetector.gui.dto.AuthenticationTokenDto;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.gui.dto.UserLoginDto;
import com.abrajner.plagiarismdetector.mapper.UserMapper;
import com.abrajner.plagiarismdetector.validator.UserLoginValidator;
import com.abrajner.plagiarismdetector.validator.Validator;

@Service
public class UserAuthenticationApplicationServiceImpl implements UserAuthenticationApplicationService {
    
    private final UserService userService;
    
    private final UserMapper userMapper;
    
    private final Validator validator;
    
    public UserAuthenticationApplicationServiceImpl(final UserService userService,
                                                    final UserMapper userMapper,
                                                    final UserLoginValidator validator) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.validator = validator;
    }
    
    @Override
    public AuthenticationTokenDto generateUserAuthenticationToken(final UserLoginDto userLoginDto) {
        this.validator.validate(userLoginDto);
        final AuthenticationTokenDto authenticationTokenDto = new AuthenticationTokenDto();
        authenticationTokenDto.setAuthenticationToken(JwtUtil.generateToken(this.userService.checkUserLoginCredentials(userLoginDto)));
        return authenticationTokenDto;
    }
    
    @Override
    public UserDto checkAuthenticationToken(final String token) {
        if(token.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return this.userMapper.convertToDto(this.userService.findUserId(JwtUtil.parseToken(token)));
    }
}

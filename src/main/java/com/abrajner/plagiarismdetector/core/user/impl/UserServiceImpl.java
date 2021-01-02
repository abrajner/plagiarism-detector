package com.abrajner.plagiarismdetector.core.user.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abrajner.plagiarismdetector.core.user.UserService;
import com.abrajner.plagiarismdetector.dao.entity.UserEntity;
import com.abrajner.plagiarismdetector.dao.repository.UserRepository;
import com.abrajner.plagiarismdetector.gui.dto.UserLoginDto;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserEntity checkUserLoginCredentials(final UserLoginDto userLoginDto) {
        final Optional<UserEntity> user = this.userRepository.findAllByLoginAndPassword(userLoginDto.getLogin(), userLoginDto.getPassword());
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Login or password is incorrect");
        }
    }
    
    @Override
    public UserEntity findUserId(final Long id) {
        final Optional<UserEntity> user = this.userRepository.findAllById(id);
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

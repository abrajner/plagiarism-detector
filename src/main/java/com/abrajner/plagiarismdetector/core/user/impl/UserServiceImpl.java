package com.abrajner.plagiarismdetector.core.user.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abrajner.plagiarismdetector.core.user.UserService;
import com.abrajner.plagiarismdetector.dao.entity.UserEntity;
import com.abrajner.plagiarismdetector.dao.repository.UserRepository;
import com.abrajner.plagiarismdetector.gui.dto.UserLoginDto;
import com.abrajner.plagiarismdetector.gui.dto.UserRegistrationDto;
import com.abrajner.plagiarismdetector.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    private final UserMapper userMapper;
    
    public UserServiceImpl(final UserRepository userRepository,
                           final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
    
    @Override
    public UserEntity saveNewUserInDatabase(final UserRegistrationDto userRegistrationDto) {
        return this.userRepository.save(this.userMapper.convertToEntity(userRegistrationDto));
    }
    
    @Override
    public void checkIfNewUserAlreadyExistsInDatabase(final String login, final String email) {
        if(this.userRepository.findAllByLogin(login).isPresent() || this.userRepository.findAllByEmail(email).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User with this login and email already exists");
        }
    }
}

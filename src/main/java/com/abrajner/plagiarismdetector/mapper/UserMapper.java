package com.abrajner.plagiarismdetector.mapper;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.dao.entity.UserEntity;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.gui.dto.UserRegistrationDto;

@Component
public class UserMapper {
    
    public UserDto convertToDto(final UserEntity userEntity){
        final UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setLogin(userEntity.getLogin());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setId(userEntity.getId());
        userDto.setActive(userEntity.isActive());
        return userDto;
    }
    
    public UserEntity convertToEntity(final UserRegistrationDto userRegistrationDto){
        return new UserEntity.Builder()
                .email(userRegistrationDto.getEmail())
                .password(userRegistrationDto.getPassword())
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .login(userRegistrationDto.getLogin()).build();
    }
}

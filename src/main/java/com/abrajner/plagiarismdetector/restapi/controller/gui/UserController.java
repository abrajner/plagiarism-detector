package com.abrajner.plagiarismdetector.restapi.controller.gui;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.applicationservice.UserApplicationService;
import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.gui.dto.UserRegistrationDto;
import com.abrajner.plagiarismdetector.restapi.controller.AbstractGuiController;

@RestController
public class UserController extends AbstractGuiController {
    
    private final UserApplicationService userApplicationService;
    
    public UserController(final UserAuthenticationApplicationService userAuthenticationApplicationService,
                          final UserApplicationService userApplicationService) {
        super(userAuthenticationApplicationService);
        this.userApplicationService = userApplicationService;
    }
    
    @GetMapping(path = "/me", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto getCurrentUserInformation(@RequestHeader("Authorization") final Optional<String> token){
        return this.checkAuthenticationToken(token.orElse(""));
    }
    
    @PostMapping(path = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto registerNewUser(@RequestBody final UserRegistrationDto userRegistrationDto){
        return this.userApplicationService.validateAndSaveNewUser(userRegistrationDto);
    }
}

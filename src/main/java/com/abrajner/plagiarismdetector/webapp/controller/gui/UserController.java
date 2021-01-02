package com.abrajner.plagiarismdetector.webapp.controller.gui;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.webapp.controller.AbstractGuiController;

@RestController
public class UserController extends AbstractGuiController {
    
    public UserController(final UserAuthenticationApplicationService userAuthenticationApplicationService) {
        super(userAuthenticationApplicationService);
    }
    
    @GetMapping(path = "/me", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto getCurrentUserInformation(@RequestHeader("Authorization") final Optional<String> token){
        return this.checkAuthenticationToken(token.orElse(""));
    }
}

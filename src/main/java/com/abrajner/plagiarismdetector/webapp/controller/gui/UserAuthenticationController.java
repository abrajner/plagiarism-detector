package com.abrajner.plagiarismdetector.webapp.controller.gui;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.gui.dto.AuthenticationTokenDto;
import com.abrajner.plagiarismdetector.gui.dto.UserLoginDto;

@RestController
public class UserAuthenticationController {
    
    private final UserAuthenticationApplicationService userAuthenticationApplicationService;
    
    public UserAuthenticationController(final UserAuthenticationApplicationService userAuthenticationApplicationService) {
        this.userAuthenticationApplicationService = userAuthenticationApplicationService;
    }
    
    @PostMapping(path = "authentication-token", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public AuthenticationTokenDto getAuthenticationToken(@RequestBody final UserLoginDto userLoginDto){
        return this.userAuthenticationApplicationService.generateUserAuthenticationToken(userLoginDto);
    }
}

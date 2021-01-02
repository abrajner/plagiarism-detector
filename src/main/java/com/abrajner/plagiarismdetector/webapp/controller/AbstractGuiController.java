package com.abrajner.plagiarismdetector.webapp.controller;

import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;

public class AbstractGuiController {
    
    private final UserAuthenticationApplicationService userAuthenticationApplicationService;
    
    public AbstractGuiController(final UserAuthenticationApplicationService userAuthenticationApplicationService) {
        this.userAuthenticationApplicationService = userAuthenticationApplicationService;
    }
    
    protected UserDto checkAuthenticationToken(final String token){
        return this.userAuthenticationApplicationService.checkAuthenticationToken(token);
    }
}

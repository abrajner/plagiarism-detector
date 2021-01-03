package com.abrajner.plagiarismdetector.webapp.controller.gui;

import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.webapp.controller.AbstractGuiController;

@RestController
public class FileUploadController extends AbstractGuiController {
    
    public FileUploadController(final UserAuthenticationApplicationService userAuthenticationApplicationService) {
        super(userAuthenticationApplicationService);
    }
    
}

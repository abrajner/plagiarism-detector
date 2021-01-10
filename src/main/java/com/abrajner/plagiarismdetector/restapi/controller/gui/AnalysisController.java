package com.abrajner.plagiarismdetector.restapi.controller.gui;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.core.user.AnalysisService;
import com.abrajner.plagiarismdetector.gui.dto.AnalyseRequest;
import com.abrajner.plagiarismdetector.gui.dto.AnalyseResponse;
import com.abrajner.plagiarismdetector.restapi.controller.AbstractGuiController;

@RestController
public class AnalysisController extends AbstractGuiController {
    
    private final AnalysisService analysisService;

    public AnalysisController(final UserAuthenticationApplicationService userAuthenticationApplicationService,
                              final AnalysisService analysisService) {
        super(userAuthenticationApplicationService);
        this.analysisService = analysisService;
    }
    
    @PostMapping(path = "/group/{group_id}/analyse", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void startFilesAnalysis(@RequestBody final AnalyseRequest analyseRequest,
                                           @RequestHeader("Authorization") final Optional<String> token,
                                           @PathVariable("group_id") final String groupId){
        this.checkAuthenticationToken(token.orElse(""));
        this.analysisService.startAnalysis(analyseRequest.getFileIds(), analyseRequest.getReportName(), Long.valueOf(groupId));
    }
    
    @PostMapping(path = "/internal/analyse", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void analyseFilesInternal(@RequestBody final AnalyseRequest analyseRequest){
    
    }
}

package com.abrajner.plagiarismdetector.restapi.controller.gui;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.core.user.ReportService;
import com.abrajner.plagiarismdetector.gui.dto.ReportsResponse;
import com.abrajner.plagiarismdetector.restapi.controller.AbstractGuiController;

@RestController
public class ReportController extends AbstractGuiController {
    
    final ReportService reportService;
    
    public ReportController(final UserAuthenticationApplicationService userAuthenticationApplicationService, final ReportService reportService) {
        super(userAuthenticationApplicationService);
        this.reportService = reportService;
    }
    
    @GetMapping(path = "group/{group_id}/reports", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ReportsResponse> getAllReportsFromGroup(@RequestHeader("Authorization") final Optional<String> token,
                                                        @PathVariable("group_id") final String groupId){
        
        this.checkAuthenticationToken(token.orElse(""));
        return this.reportService.getAllReportsForGroup(Long.valueOf(groupId));
    }
}

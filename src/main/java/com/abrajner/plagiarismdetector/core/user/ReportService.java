package com.abrajner.plagiarismdetector.core.user;

import java.util.List;

import com.abrajner.plagiarismdetector.gui.dto.ReportsResponse;

public interface ReportService {
    
    boolean isReportGenerationFinished(Long groupId, String reportName);
    
    List<ReportsResponse> getAllReportsForGroup(Long groupId);
}

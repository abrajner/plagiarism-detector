package com.abrajner.plagiarismdetector.core.user;

import java.util.List;

import com.abrajner.plagiarismdetector.gui.dto.AnalyseResponse;

public interface AnalysisService {
    
    void startAnalysis(List<Long> fileIds, String reportName, Long groupId);
    
    void performAnalysis(Long groupId, String reportName);
}

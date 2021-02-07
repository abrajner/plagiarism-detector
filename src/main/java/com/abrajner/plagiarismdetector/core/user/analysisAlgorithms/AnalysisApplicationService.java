package com.abrajner.plagiarismdetector.core.user.analysisAlgorithms;

import java.util.List;

import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public interface AnalysisApplicationService {
    
    ReportEntity performAnalysis(ParsedFile firstFile,
                                 ParsedFile secondFile);
    
}
package com.abrajner.plagiarismdetector.plagiarismanalysis;

import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public interface AnalysisApplicationService {
    
    ReportEntity performAnalysis(ParsedFile firstFile,
                                 ParsedFile secondFile);
    
}
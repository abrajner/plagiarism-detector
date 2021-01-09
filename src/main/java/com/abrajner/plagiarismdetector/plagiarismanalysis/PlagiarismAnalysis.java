package com.abrajner.plagiarismdetector.plagiarismanalysis;

import java.util.List;

public interface PlagiarismAnalysis {
    
    double analyze(List<String> firstFile, List<String> secondFile);
}

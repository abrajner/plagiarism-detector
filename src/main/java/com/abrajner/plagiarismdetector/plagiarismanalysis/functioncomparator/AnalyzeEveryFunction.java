package com.abrajner.plagiarismdetector.plagiarismanalysis.functioncomparator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.fileparser.ParsedFile;
import com.abrajner.plagiarismdetector.plagiarismanalysis.AnalysisApplicationService;
import com.abrajner.plagiarismdetector.plagiarismanalysis.CharacterAnalysis;

@Component
public class AnalyzeEveryFunction implements AnalysisApplicationService {
    
    private final CharacterAnalysis characterAnalysis;
    
    public AnalyzeEveryFunction(final CharacterAnalysis characterAnalysis) {
        this.characterAnalysis = characterAnalysis;
    }
    
    @Override
    public ReportEntity performAnalysis(final ParsedFile firstFile, final ParsedFile secondFile) {
        
        final ReportEntity reportEntity = new ReportEntity();
        double codeSimilarityPercentage;
        if(!firstFile.getIdentifiers().isEmpty() || !secondFile.getIdentifiers().isEmpty()){
            codeSimilarityPercentage = this.analyze(
                    this.replaceValues(firstFile.getIdentifiers(), firstFile.getFileContentByFunctions()),
                    this.replaceValues(secondFile.getIdentifiers(), secondFile.getFileContentByFunctions()));
        }else {
            codeSimilarityPercentage = this.analyze(
                    firstFile.getFileContentByFunctions(),
                    secondFile.getFileContentByFunctions());
        }
        BigDecimal bigDecimal = new BigDecimal(codeSimilarityPercentage* 100.0);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        codeSimilarityPercentage = bigDecimal.doubleValue();
        reportEntity.setPlagiarism(codeSimilarityPercentage >= 50);
        reportEntity.setFinished(true);
        reportEntity.setCodeSimilarityPercentage(codeSimilarityPercentage);
        return reportEntity;
    }
    
    private List<List<String>> replaceValues(final List<String> identifiers, final List<List<String>> fileContent){
        identifiers.forEach(id -> fileContent.forEach(line -> line.forEach(token -> {
            if(token.equals(id)){
                line.set(line.indexOf(token), "<identifier>");
            }
        })));
        return fileContent;
    }
    
    private double analyze(final List<List<String>> firstFile, final List<List<String>> secondFile){
        final List<Double> longestCommonSubsequenceForChars = new ArrayList<>();
        Double sumOfLongestCommonSubsequenceForChars = (double) 0;
    
        for (final List<String> lineFromFirstFile : firstFile) {
            longestCommonSubsequenceForChars.clear();
            for (final List<String> lineFromSecondFile : secondFile) {
                longestCommonSubsequenceForChars.add(this.characterAnalysis.analyze(
                        this.prepareDataForCharacterAnalysis(lineFromFirstFile),
                        this.prepareDataForCharacterAnalysis(lineFromSecondFile)));
            }
    
            sumOfLongestCommonSubsequenceForChars += Collections.max(longestCommonSubsequenceForChars);
        }
        
        return ((sumOfLongestCommonSubsequenceForChars * 2)/(double) (firstFile.size() + secondFile.size()));
    }
    
    private String prepareDataForCharacterAnalysis(final List<String> data){
        final StringBuilder firstValue = new StringBuilder();
        data.forEach(firstValue::append);
        return firstValue.toString();
    }
}

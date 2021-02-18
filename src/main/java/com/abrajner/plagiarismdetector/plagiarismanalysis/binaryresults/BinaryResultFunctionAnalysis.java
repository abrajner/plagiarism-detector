package com.abrajner.plagiarismdetector.plagiarismanalysis.binaryresults;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.fileparser.ParsedFile;
import com.abrajner.plagiarismdetector.plagiarismanalysis.AnalysisApplicationService;

@Component
public class BinaryResultFunctionAnalysis implements AnalysisApplicationService {
    
    private final BinaryResultAnalysis binaryResultAnalysis;
    
    public BinaryResultFunctionAnalysis(final BinaryResultAnalysis binaryResultAnalysis) {
        this.binaryResultAnalysis = binaryResultAnalysis;
    }
    
    @Override
    public ReportEntity performAnalysis(final ParsedFile firstFile, final ParsedFile secondFile) {
    
        final ReportEntity reportEntity = new ReportEntity();
        double codeSimilarityPercentage;
        
        if(!firstFile.getIdentifiers().isEmpty() || !secondFile.getIdentifiers().isEmpty()){
            codeSimilarityPercentage = this.binaryResultAnalysis.analyze(
                    this.replaceValues(firstFile.getIdentifiers(), firstFile.getFileContentByFunctions()),
                    this.replaceValues(secondFile.getIdentifiers(), secondFile.getFileContentByFunctions()));
        }else {
            codeSimilarityPercentage = this.binaryResultAnalysis.analyze(
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
}

package com.abrajner.plagiarismdetector.core.user.analysisAlgorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.fileparser.ParsedFile;
import com.abrajner.plagiarismdetector.plagiarismanalysis.LongestCommonSubsequenceAnalysis;

@Service
public class LineByLineAnalysis implements AnalysisApplicationService {
    
    final LongestCommonSubsequenceAnalysis longestCommonSubsequenceAnalysis;
    
    public LineByLineAnalysis() {
        this.longestCommonSubsequenceAnalysis = new LongestCommonSubsequenceAnalysis();
    }
    
    @Override
    public ReportEntity performAnalysis(final ParsedFile firstFile,
            final ParsedFile secondFile) {
        final ReportEntity reportEntity = new ReportEntity();
        double codeSimilarityPercentage = this.longestCommonSubsequenceAnalysis.analyze(
                firstFile.getFileContentByInstructions(),
                firstFile.getIdentifiersByEquals(),
                secondFile.getFileContentByInstructions(),
                secondFile.getIdentifiersByEquals(),
                false);
        BigDecimal bigDecimal = new BigDecimal(codeSimilarityPercentage* 100.0);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        codeSimilarityPercentage = bigDecimal.doubleValue();
        
        double codeSimilarityPercentageWithSubstitution = 0.0;
        boolean isSubstitutionIncluded = false;
        if(!this.longestCommonSubsequenceAnalysis.getTokensForSubstitution().isEmpty()){
            isSubstitutionIncluded = true;
            if(firstFile.getFileContentByInstructions().size() >= secondFile.getFileContentByInstructions().size()){
                codeSimilarityPercentageWithSubstitution = this.longestCommonSubsequenceAnalysis.analyze(
                        this.replaceValues(this.longestCommonSubsequenceAnalysis.getTokensForSubstitution(), firstFile.getFileContentByInstructions()),
                        firstFile.getIdentifiersByEquals(),
                        secondFile.getFileContentByInstructions(),
                        secondFile.getIdentifiersByEquals(),
                        true);
                BigDecimal bigDecimal1 = new BigDecimal(codeSimilarityPercentageWithSubstitution* 100.0);
                bigDecimal1 = bigDecimal1.setScale(2, RoundingMode.HALF_UP);
                codeSimilarityPercentageWithSubstitution = bigDecimal1.doubleValue();
            }
            else{
                codeSimilarityPercentageWithSubstitution = this.longestCommonSubsequenceAnalysis.analyze(
                        firstFile.getFileContentByInstructions(),
                        firstFile.getIdentifiersByEquals(),
                        this.replaceValues(this.longestCommonSubsequenceAnalysis.getTokensForSubstitution(), secondFile.getFileContentByInstructions()),
                        secondFile.getIdentifiersByEquals(),
                        true);
                BigDecimal bigDecimal2 = new BigDecimal(codeSimilarityPercentageWithSubstitution* 100.0);
                bigDecimal2 = bigDecimal2.setScale(2, RoundingMode.HALF_UP);
                codeSimilarityPercentageWithSubstitution = bigDecimal2.doubleValue();
            }
            reportEntity.setPlagiarism(codeSimilarityPercentageWithSubstitution >= 0.5);
        }else {
            reportEntity.setPlagiarism(codeSimilarityPercentage >= 0.5);
            }
            reportEntity.setSubstitutionIncluded(isSubstitutionIncluded);
            reportEntity.setFinished(true);
            reportEntity.setCodeSimilarityPercentageWithSubstitution(codeSimilarityPercentageWithSubstitution);
            reportEntity.setCodeSimilarityPercentage(codeSimilarityPercentage);
            return reportEntity;
    }
    
    private List<List<String>> replaceValues(final Map<String, String> tokensForSubstitution, final List<List<String>> fileContent){
        tokensForSubstitution.forEach((key, value) -> {
            fileContent.forEach(line -> {
                if(line.contains(key)){
                    line.set(line.indexOf(key), value);
                }
            });
        });
        return fileContent;
    }
}

package com.abrajner.plagiarismdetector.plagiarismanalysis.complexresults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.plagiarismanalysis.CharacterAnalysis;

@Component
public class ComplexResultAnalysis {
    
    private final CharacterAnalysis characterAnalysis;
    
    public ComplexResultAnalysis(final CharacterAnalysis characterAnalysis) {
        this.characterAnalysis = characterAnalysis;
    }
    
    public double analyze(final List<List<String>> firstFile, final List<List<String>> secondFile) {
        List<Double> A = new ArrayList<>(Collections.nCopies(secondFile.size() + 1, 0.0));
        for(int i = 0; i <= firstFile.size(); i++){
            final List<Double> B = new ArrayList<>(Collections.nCopies(secondFile.size() + 1, 0.0));
            for(int j = 0; j <= secondFile.size(); j++){
                final double similarity;
                if(i == 0 || j == 0){
                    B.set(j, 0.0);
                }
                else {
                    similarity = A.get(j-1) + this.characterAnalysis.analyze(this.prepareDataForCharacterAnalysis(firstFile.get(i - 1)),
                            this.prepareDataForCharacterAnalysis(secondFile.get(j - 1)));
                    B.set(j, Math.max(A.get(j), Math.max(B.get(j-1), similarity)));
                }
            }
            A = B;
        }
        
        return this.calculateResultForLine(A.get(A.size() - 1), firstFile.size(), secondFile.size());
    }
    
    private String prepareDataForCharacterAnalysis(final List<String> data){
        final StringBuilder firstValue = new StringBuilder();
        data.forEach(firstValue::append);
        return firstValue.toString();
    }
    
    public double calculateResultForLine(final Double longestCommonSubsequence,
                                         final Integer firstLineCapacity,
                                         final Integer secondLineCapacity) {
        return ((longestCommonSubsequence * 2)/ (double) (firstLineCapacity + secondLineCapacity));
    }
}

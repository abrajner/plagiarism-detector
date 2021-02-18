package com.abrajner.plagiarismdetector.plagiarismanalysis.binaryresults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.plagiarismanalysis.CharacterAnalysis;

@Component
public class BinaryResultAnalysis {
    
    private final CharacterAnalysis characterAnalysis;
    
    public BinaryResultAnalysis(final CharacterAnalysis characterAnalysis) {
        this.characterAnalysis = characterAnalysis;
    }
    
    public double analyze(final List<List<String>> firstFile, final List<List<String>> secondFile) {
        List<Integer> A = new ArrayList<>(Collections.nCopies(secondFile.size() + 1, 0));
        for(int i = 0; i <= firstFile.size(); i++){
            final List<Integer> B = new ArrayList<>(Collections.nCopies(secondFile.size() + 1, 0));
            for(int j = 0; j <= secondFile.size(); j++){
                if(i == 0 || j == 0){
                    B.set(j, 0);
                } else if(this.characterAnalysis.analyze(this.prepareDataForCharacterAnalysis(firstFile.get(i - 1)),
                        this.prepareDataForCharacterAnalysis(secondFile.get(j - 1))) == 1.0){
                    B.set(j, A.get(j-1) + 1);
                }
                else {
                    B.set(j, Math.max(A.get(j), B.get(j-1)));
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
    
    public double calculateResultForLine(final Integer longestCommonSubsequence,
                                         final Integer firstLineCapacity,
                                         final Integer secondLineCapacity) {
        return ( (double) (longestCommonSubsequence * 2)/ (double) (firstLineCapacity + secondLineCapacity));
    }
}

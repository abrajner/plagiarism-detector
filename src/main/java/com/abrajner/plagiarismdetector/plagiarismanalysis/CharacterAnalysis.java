package com.abrajner.plagiarismdetector.plagiarismanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CharacterAnalysis{
    
    public double analyze(final String firstFile, final String secondFile) {
        List<Integer> A = new ArrayList<>(Collections.nCopies(secondFile.length() + 1, 0));
    
        for(int i = 0; i <= firstFile.length(); i++){
            final List<Integer> B = new ArrayList<>(Collections.nCopies(secondFile.length() + 1, 0));
            for(int j = 0; j <= secondFile.length(); j++){
                if(i == 0 || j == 0){
                    B.set(j, 0);
                }else if(firstFile.charAt(i-1) == secondFile.charAt(j-1)){
                    B.set(j, A.get(j-1) + 1);
                }else {
                    B.set(j, Math.max(A.get(j), B.get(j-1)));
                }
            }
            A = B;
        }
        return this.calculateResultForLine(A.get(A.size() - 1), firstFile.length(), secondFile.length());
    }
    
    private double calculateResultForLine(final Integer longestCommonSubsequence,
                                          final Integer charactersInFirstLine,
                                          final Integer charactersInSecondLine){
        return ((double) (longestCommonSubsequence * 2)/ (double) (charactersInFirstLine + charactersInSecondLine));
    }
}

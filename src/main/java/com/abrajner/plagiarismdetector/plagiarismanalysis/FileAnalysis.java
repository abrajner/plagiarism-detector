package com.abrajner.plagiarismdetector.plagiarismanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class FileAnalysis {

    private final CharacterAnalysis characterAnalysis = new CharacterAnalysis();
    private TokenAnalysis tokenAnalysis;
    
    public double analyze(final List<List<String>> fileA,
                          final List<String> fileAIds,
                          final List<List<String>> fileB,
                          final List<String> fileBIds,
                          final boolean withSubstitution) {
        if(fileA.size() >= fileB.size()){
            this.tokenAnalysis = new TokenAnalysis(fileAIds);
            return this.analyzeFiles(fileA, fileB, withSubstitution);
        }
        else {
            this.tokenAnalysis = new TokenAnalysis(fileBIds);
            return this.analyzeFiles(fileB, fileA, withSubstitution);
        }
    }
    
    private double analyzeFiles(final List<List<String>> firstFile, final List<List<String>> secondFile, final boolean withSubstitution){
        final List<Double> longestCommonSubsequenceForChars = new ArrayList<>();
        final List<Double> longestCommonSubsequenceForTokens = new ArrayList<>();
        
        Double sumOfLongestCommonSubsequenceForChars = (double) 0;
        Double sumOfLongestCommonSubsequenceForTokens = (double) 0;
    
        final AtomicReference<Integer> firstFileCharsCapacity = new AtomicReference<>(0);
        final AtomicReference<Integer> secondFileCharsCapacity = new AtomicReference<>(0);
        final AtomicReference<Integer> firstFileTokensCapacity = new AtomicReference<>(0);
        final AtomicReference<Integer> secondFileTokensCapacity = new AtomicReference<>(0);

        firstFile.forEach(line -> {
            firstFileTokensCapacity.updateAndGet(v -> v + line.size());
            final AtomicReference<Integer> lineCapacity = new AtomicReference<>(0);
            line.forEach(token -> lineCapacity.updateAndGet(v -> v + token.length()));
            firstFileCharsCapacity.updateAndGet(v -> v + lineCapacity.get());
        });
        secondFile.forEach(line -> {
            secondFileTokensCapacity.updateAndGet(v -> v + line.size());
            final AtomicReference<Integer> lineCapacity = new AtomicReference<>(0);
            line.forEach(token -> lineCapacity.updateAndGet(v -> v + token.length()));
            secondFileCharsCapacity.updateAndGet(v -> v + lineCapacity.get());
        });
    
        for (final List<String> lineFromFirstFile : firstFile) {
            longestCommonSubsequenceForChars.clear();
            longestCommonSubsequenceForTokens.clear();
            for (final List<String> lineFromSecondFile : secondFile) {
                longestCommonSubsequenceForTokens.add(this.tokenAnalysis.analyze(
                        lineFromFirstFile, lineFromSecondFile, withSubstitution));
                longestCommonSubsequenceForChars.add(this.characterAnalysis.analyze(
                        this.prepareDataForCharacterAnalysis(lineFromFirstFile),
                        this.prepareDataForCharacterAnalysis(lineFromSecondFile)));
            }
            
            sumOfLongestCommonSubsequenceForChars += Collections.max(longestCommonSubsequenceForChars);
            sumOfLongestCommonSubsequenceForTokens += Collections.max(longestCommonSubsequenceForTokens);
        }
        
        this.tokenAnalysis.getSubstitutedIds().forEach((key, value) -> System.out.println(key + " " + value));
        return (this.calculateResultForFile(sumOfLongestCommonSubsequenceForTokens,
                firstFileTokensCapacity.get(),
                secondFileTokensCapacity.get())
                + this.calculateResultForFile(sumOfLongestCommonSubsequenceForChars,
                firstFileCharsCapacity.get(),
                secondFileCharsCapacity.get()))/(double) 2;
    }
    
    public Map<String, String> getTokensForSubstitution(){
        return this.tokenAnalysis.getSubstitutedIds();
    }
    
    private double calculateResultForFile(final Double longestCommonSubsequence,
                                         final Integer firstFileCapacity,
                                         final Integer secondFileCapacity) {
        return  longestCommonSubsequence/
                ((double) firstFileCapacity + (double) secondFileCapacity - longestCommonSubsequence);
    }
    
    private String prepareDataForCharacterAnalysis(final List<String> data){
        final StringBuilder firstValue = new StringBuilder();
        data.forEach(firstValue::append);
        return firstValue.toString();
    }
}

package com.abrajner.plagiarismdetector.plagiarismanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class LongestCommonSubsequenceAnalysis {

    private final CharacterAnalysis characterAnalysis = new CharacterAnalysis();
    private TokenAnalysis tokenAnalysis;
    
    public double analyze(final List<List<String>> fileA,
                          final List<String> fileAIds,
                          final List<List<String>> fileB,
                          final List<String> fileBIds,
                          final boolean withSubstitution) {
        if(fileA.size() >= fileB.size()){
            this.tokenAnalysis = new TokenAnalysis(fileAIds, fileBIds);
            return this.analyzeFiles(fileA, fileB, withSubstitution);
        }
        else {
            this.tokenAnalysis = new TokenAnalysis(fileBIds, fileAIds);
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
    
        this.getFileCapacity(firstFile, firstFileCharsCapacity, firstFileTokensCapacity);
        this.getFileCapacity(secondFile, secondFileCharsCapacity, secondFileTokensCapacity);
    
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
        
        return (this.calculateResultForFile(sumOfLongestCommonSubsequenceForTokens,
                firstFileTokensCapacity.get(),
                secondFileTokensCapacity.get())
                + this.calculateResultForFile(sumOfLongestCommonSubsequenceForChars,
                firstFileCharsCapacity.get(),
                secondFileCharsCapacity.get()))/(double) 2;
    }
    
    private void getFileCapacity(final List<List<String>> file, final AtomicReference<Integer> fileCharsCapacity, final AtomicReference<Integer> fileTokensCapacity) {
        file.forEach(line -> {
            fileTokensCapacity.updateAndGet(v -> v + line.size());
            final AtomicReference<Integer> lineCapacity = new AtomicReference<>(0);
            line.forEach(token -> lineCapacity.updateAndGet(v -> v + token.length()));
            fileCharsCapacity.updateAndGet(v -> v + lineCapacity.get());
        });
    }
    
    public Map<String, String> getTokensForSubstitution(){
        return this.tokenAnalysis.getSubstitutedIds();
    }
    
    private double calculateResultForFile(final Double longestCommonSubsequence,
                                         final Integer firstFileCapacity,
                                         final Integer secondFileCapacity) {
        return  longestCommonSubsequence * 2/
                ((double) firstFileCapacity + (double) secondFileCapacity);
    }
    
    private String prepareDataForCharacterAnalysis(final List<String> data){
        final StringBuilder firstValue = new StringBuilder();
        data.forEach(firstValue::append);
        return firstValue.toString();
    }
}

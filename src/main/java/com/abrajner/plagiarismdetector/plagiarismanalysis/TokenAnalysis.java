package com.abrajner.plagiarismdetector.plagiarismanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TokenAnalysis{
    
    final Map<String, String> substitutedIds = new HashMap<>();
    
    private final List<String> idsInFirstFile;
    
    public TokenAnalysis(final List<String> idsInFirstFile){
        this.idsInFirstFile = new ArrayList<>(idsInFirstFile);
    }
    
    public Map<String, String> getSubstitutedIds() {
        return Collections.unmodifiableMap(this.substitutedIds);
    }
    
    public double analyze(final List<String> firstFile, final List<String> secondFile, final boolean substitutionIncluded) {
        List<Integer> A = new ArrayList<>(Collections.nCopies(secondFile.size() + 1, 0));
        for(int i = 0; i <= firstFile.size(); i++){
            final List<Integer> B = new ArrayList<>(Collections.nCopies(secondFile.size() + 1, 0));
            for(int j = 0; j <= secondFile.size(); j++){
                if(i == 0 || j == 0){
                    B.set(j, 0);
                }else if(firstFile.get(i - 1).equals(secondFile.get(j - 1))){
                    B.set(j, A.get(j-1) + 1);
                }else {
                    B.set(j, Math.max(A.get(j), B.get(j-1)));
                }
            }
            A = B;
        }
        
        if(!substitutionIncluded){
            this.findIdsForSubstitution(A.get(A.size() - 1), firstFile, secondFile);
        }
        
        return this.calculateResultForLine(A.get(A.size() - 1), firstFile.size(), secondFile.size());
    }
    
    public double calculateResultForLine(final Integer longestCommonSubsequence,
                                         final Integer firstLineCapacity,
                                         final Integer secondLineCapacity) {
        final Integer minLine = (firstLineCapacity >= secondLineCapacity) ? secondLineCapacity : firstLineCapacity;
        return ((double) longestCommonSubsequence/ (double) minLine) * (double)longestCommonSubsequence;
    }
    
    private void findIdsForSubstitution(final Integer longestCommonSubsequence,
                                        final List<String> firstFile,
                                        final List<String> secondFile){
        
        final Optional<String> identifierInLine = this.findIdentifierInLine(this.idsInFirstFile, firstFile);
        
        identifierInLine.ifPresent(value -> {
            if(firstFile.indexOf(value) != (firstFile.size() -1)) {
                if ("=".equals(firstFile.get(firstFile.indexOf(value) + 1))) {
        
                    if (!this.substitutedIds.containsKey(value)
                            && (firstFile.size() - firstFile.indexOf(value) - 1) <= longestCommonSubsequence) {
            
                        final String idFromSecondFile = secondFile.get(secondFile.indexOf("=") - 1);
                        if (!value.equals(idFromSecondFile)
                                && !this.substitutedIds.containsValue(idFromSecondFile)) {
                
                            this.substitutedIds.put(value, secondFile.get(secondFile.indexOf("=") - 1));
                        }
                    }
                }
            }
        });
    }
    
    
    private Optional<String> findIdentifierInLine(final List<String> identifiersList,
                                                  final List<String> line){
        if(identifiersList.isEmpty()){
            return Optional.empty();
        }
        if(identifiersList.stream().anyMatch(line::contains)){
            return Optional.of(identifiersList.stream().filter(line::contains).collect(Collectors.joining()));
        }
        return Optional.empty();
    }
}

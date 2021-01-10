package com.abrajner.plagiarismdetector.fileparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.abrajner.plagiarismdetector.common.Defaults;

public class TokenizedStringSerializer {
    
    public static String serialize(final List<Object> listOfTokens){
        final StringBuilder parsedTokens = new StringBuilder();
        listOfTokens.forEach(token -> parsedTokens.append(token.toString()).append(Defaults.TOKENS_DELIMITER));
        return parsedTokens.toString();
    }
    
    public static List<List<String>> deserializeTokens(final String serializedData){
        final List<String> list = Arrays.asList(serializedData.split(Defaults.TOKENS_DELIMITER));
        final List<List<String>> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        result.add(new ArrayList<>());
        while(i<list.size()){
            final String token = list.get(i);
            if(!"\n".equals(token)){
                result.get(j).add(token);
            }else {
                result.add(new ArrayList<>());
                j ++;
            }
            i ++;
        }
        
        return result
                .stream()
                .filter(tokens -> !tokens.isEmpty())
                .collect(Collectors.toList());
    }
    
    public static List<String> deserializeIdentifiers(final String serializedData){
        return Arrays.asList(serializedData.split(Defaults.TOKENS_DELIMITER));
    }
    
    public static List<String> deserializeLines(final String serializedData){
        final List<String> list = Arrays.asList(serializedData.split(Defaults.TOKENS_DELIMITER));
        final StringBuilder tokensLine = new StringBuilder();
        final List<String> result = new ArrayList<>();
        list.forEach(token -> {
            if(!"\n".equals(token)){
                tokensLine.append(token);
            }else {
                result.add(tokensLine.toString());
                tokensLine.delete(0, tokensLine.length());
            }
        });
        result.add(tokensLine.toString());
        return result
                .stream()
                .filter(tokens -> !tokens.isEmpty())
                .collect(Collectors.toList());
    }
}

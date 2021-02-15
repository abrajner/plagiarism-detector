package com.abrajner.plagiarismdetector.fileparser.languageparser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public class CommonParser {
    
    public static List<List<String>> getInstructionsFromFile(final ParsedFile parsedFile) {
        final List<List<String>> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        result.add(new ArrayList<>());
        while (i < parsedFile.getFileContent().size()) {
            final String token = parsedFile.getFileContent().get(i);
            if (";".equals(token) || "{".equals(token) || "}".equals(token)) {
                result.get(j).add(token);
                result.add(new ArrayList<>());
                j++;
            }
            else if (!"\n".equals(token)) {
                if (!"\t".equals(token)) {
                    result.get(j).add(token);
                }
            }
            else {
                result.add(new ArrayList<>());
                j++;
            }
            i++;
        }
        return result
                .stream()
                .filter(tokens -> !tokens.isEmpty())
                .collect(Collectors.toList());
    }
    
    public static List<List<String>> functionsParser(final ParsedFile parsedFile, final List<String> returnedTypes, final List<String> identifiers, final List<String> keywords) {
        final List<List<String>> result = new ArrayList<>();
        final List<List<String>> fileContent = new ArrayList<>(parsedFile.getFileContentByInstructions());
        int k = 0;
        boolean isFunctionEntered = false;
        int numberOfEnteredBrackets = 0;
        result.add(new ArrayList<>());
        for(final List<String> line: fileContent){
            boolean isInstructionWithReturnType = false;
            boolean createNewArrayForFunction = false;
            if(line.contains("(") && line.contains(")") && line.contains("{")){
                createNewArrayForFunction = true;
            }
            for(final String currentValue: line){
                if(returnedTypes.contains(currentValue)){
                    if(createNewArrayForFunction){
                        createNewArrayForFunction = false;
                        result.add(new ArrayList<>());
                        k ++;
                    }
                    result.get(k).add(currentValue);
                    isInstructionWithReturnType = true;
                    continue;
                }
                if(isInstructionWithReturnType){
                    if(!keywords.contains(currentValue) && !NumberUtils.isCreatable(currentValue) && !identifiers.contains(currentValue)){
                        identifiers.add(currentValue);
                    }
                }
                if("{".equals(currentValue) && isInstructionWithReturnType){
                    numberOfEnteredBrackets++;
                    isFunctionEntered = true;
                }
                if ("}".equals(currentValue)){
                    numberOfEnteredBrackets--;
                    if(isFunctionEntered && numberOfEnteredBrackets<1) {
                        result.get(k).add(currentValue);
                        isFunctionEntered = false;
                        continue;
                    }
                }
                result.get(k).add(currentValue);
            }
        }
        return result;
    }
}

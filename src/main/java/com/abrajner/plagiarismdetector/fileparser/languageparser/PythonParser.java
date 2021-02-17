package com.abrajner.plagiarismdetector.fileparser.languageparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public class PythonParser implements LanguageParser {
    
    private ParsedFile parsedFile;
    
    private final List<String> identifiers = new ArrayList<>();
    
    final static List<String> CHARACTERS_FROM_FUNCTION_DEFINITION = Arrays.asList(",", "(", ")", ":", "*");
    
    @Override
    public void parse(final ParsedFile parsedFile) {
        this.parsedFile = parsedFile;
        parsedFile.getFileContentByInstructions().addAll(this.instructionsParser());
        this.identifiers.addAll(parsedFile.getIdentifiersByEquals());
        parsedFile.getFileContentByFunctions().addAll(this.functionsParser());
        this.parsedFile.getIdentifiersByParser().addAll(this.identifiers);
    }
    
    private List<List<String>> instructionsParser(){
        final List<List<String>> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        result.add(new ArrayList<>());
        while(i < this.parsedFile.getFileContent().size()){
            final String token = this.parsedFile.getFileContent().get(i);
            if(!"\n".equals(token)){
                result.get(j).add(token);
            }
            else {
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
    
    private List<List<String>> functionsParser() {
        final List<List<String>> result = new ArrayList<>();
        final List<List<String>> fileContent = new ArrayList<>(this.parsedFile.getFileContentByInstructions());
        int k = 0;
        int functionNumberOfTabs = 0;
        boolean isFunctionModeEntered = false;
        boolean isStringEntered = false;
        result.add(new ArrayList<>());
        for (final List<String> lines : fileContent) {
            int currentNumberOfTabs = 0;
            boolean isNewLine = true;
            boolean isFunctionDefinitionLine = false;
            for (final String currentValue : lines) {
                if("\"".equals(currentValue)){
                    isStringEntered = !isStringEntered;
                    result.get(k).add(currentValue);
                    continue;
                }
                if(isStringEntered){
                    result.get(k).add(currentValue);
                    continue;
                }
                if(isFunctionDefinitionLine && !CHARACTERS_FROM_FUNCTION_DEFINITION.contains(currentValue) &&
                        !this.identifiers.contains(currentValue) && !NumberUtils.isCreatable(currentValue)){
                    this.identifiers.add(currentValue);
                }
                if ("\t".equals(currentValue)) {
                    result.get(k).add(currentValue);
                    currentNumberOfTabs++;
                    isNewLine = false;
                    continue;
                }
                if (isFunctionModeEntered && functionNumberOfTabs < currentNumberOfTabs) {
                    result.get(k).add(currentValue);
                    continue;
                }else if (isFunctionModeEntered && isNewLine) {
                    functionNumberOfTabs = 0;
                    result.add(new ArrayList<>());
                    isFunctionModeEntered = false;
                    k++;
                }
                if ("def".equals(currentValue)) {
                    functionNumberOfTabs = currentNumberOfTabs;
                    isFunctionModeEntered = true;
                    isFunctionDefinitionLine = true;
                    if (!result.get(k).isEmpty()) {
                        result.add(new ArrayList<>());
                        k++;
                    }
                    result.get(k).add(currentValue);
                    isNewLine = false;
                    continue;
                }
                result.get(k).add(currentValue);
                isNewLine = false;
            }
            result.get(k).add("\n");
        }
        return result;
    }
}

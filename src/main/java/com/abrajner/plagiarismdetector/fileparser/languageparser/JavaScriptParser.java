package com.abrajner.plagiarismdetector.fileparser.languageparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public class JavaScriptParser implements LanguageParser {
    
    private ParsedFile parsedFile;
    
    private final List<String> identifiers = new ArrayList<>();
    
    final private static List<String> CHARACTERS_FROM_FUNCTION_DEFINITION = Arrays.asList("function", "=", ">", "{",
            "}", "(", ")", ",", "var", "let", "const");
    
    @Override
    public void parse(final ParsedFile parsedFile) {
        this.parsedFile = parsedFile;
        this.identifiers.addAll(parsedFile.getIdentifiersByEquals());
        parsedFile.getFileContentByInstructions().addAll(this.instructionsParser());
        parsedFile.getFileContentByFunctions().addAll(this.functionsParser());
        this.parsedFile.getIdentifiersByParser().addAll(this.identifiers);
    }
    
    private List<List<String>> instructionsParser() {
        return CommonParser.getInstructionsFromFile(this.parsedFile);
    }
    
    private List<List<String>> functionsParser() {
        final List<List<String>> result = new ArrayList<>();
        final List<List<String>> fileContent = new ArrayList<>(this.parsedFile.getFileContentByInstructions());
        int k = 0;
        boolean isFunctionEntered = false;
        int numberOfEnteredBrackets = 0;
        result.add(new ArrayList<>());
        for(final List<String> line: fileContent){
            boolean isInstructionWithFunctionDefinition = false;
            boolean createNewArrayForFunction = false;
            if(line.contains("function")){
                createNewArrayForFunction = true;
            }
            for(final String currentValue: line){
                if(createNewArrayForFunction){
                    createNewArrayForFunction = false;
                    result.add(new ArrayList<>());
                    k ++;
                    
                    result.get(k).add(currentValue);
                    isInstructionWithFunctionDefinition = true;
                    continue;
                }
                if(isInstructionWithFunctionDefinition){
                    if(!CHARACTERS_FROM_FUNCTION_DEFINITION.contains(currentValue) && !NumberUtils.isCreatable(currentValue)
                            && !this.identifiers.contains(currentValue)){
                        this.identifiers.add(currentValue);
                    }
                }
                if("{".equals(currentValue) && isInstructionWithFunctionDefinition){
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
        return result;    }
}
